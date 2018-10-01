package com.daqa.quiz;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daqa.R;
import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.QuizData;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class QuizActivity extends DaggerAppCompatActivity implements IQuizContract.IQuizView {

    @Inject
    Lazy<IQuizContract.IQuizPresenter> mPresenterLazy_;

    private TextView mTvQuestion_;
    private TextView mTvScore_;
    private PieChart mChart_;

    private static final int QUESTION_DELAY = 1200;
    private View mTvLoading_;
    private View mLlQuiz_;
    private View mLlFinalScore_;
    private QuizAdapter mQuizAdapter_;
    private ImageView mIvQuiz_;
    private View mCvQuiz_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mTvQuestion_ = findViewById(R.id.tv_question);
        mTvScore_ = findViewById(R.id.tv_score);
        mChart_ = findViewById(R.id.chart);
        mLlQuiz_ = findViewById(R.id.ll_quiz);
        mIvQuiz_ = findViewById(R.id.iv_quiz);
        mCvQuiz_ = findViewById(R.id.cv_quiz);
        mLlFinalScore_ = findViewById(R.id.ll_final_score);
        mTvLoading_ = findViewById(R.id.tv_loading);
        mPresenterLazy_.get().initialise();
        RecyclerView rv = findViewById(R.id.rv_options);
        rv.setHasFixedSize(true);
        mQuizAdapter_ = new QuizAdapter(new QuizAdapter.OnOptionClickListener() {
            @Override
            public void onClick(int optionId) {
                mPresenterLazy_.get().setScore();
                nextQuestion();
            }
        }, getLayoutInflater());
        rv.setAdapter(mQuizAdapter_);
    }

    @Override
    public void showLoadingScreen() {
        mLlFinalScore_.setVisibility(View.GONE);
        mTvLoading_.setVisibility(View.VISIBLE);
        mLlQuiz_.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showQuizScreen() {
        mLlQuiz_.setVisibility(View.VISIBLE);
        mLlFinalScore_.setVisibility(View.GONE);
        mTvLoading_.setVisibility(View.GONE);
    }

    @Override
    public void showQuiz(final QuizData quizData) {
        mCvQuiz_.setVisibility(View.GONE);
        mTvQuestion_.setText(quizData.getQuestion().getQuestion());
        mQuizAdapter_.setQuizData(quizData);
    }

    private void nextQuestion() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLlQuiz_.setAlpha(0.0f);
                mPresenterLazy_.get().nextQuestion();
                mLlQuiz_
                        .animate()
                        .setDuration(500)
                        .alpha(1.0f);

            }
        }, QUESTION_DELAY);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage(R.string.warning_message).setCancelable(false).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public void showScore(int score, int total) {
        mTvScore_.setText(getString(R.string.score_format, score, total));
    }

    @Override
    public void showFinalScore(int scorePerc) {
        mTvLoading_.setVisibility(View.GONE);
        mLlQuiz_.setVisibility(View.GONE);
        mTvScore_.setText(null);
        mLlFinalScore_.setVisibility(View.VISIBLE);
        updateGraph(scorePerc);
    }

    public void reset(View view) {
        mPresenterLazy_.get().reset();
    }


    @Override
    public void showImage(Image image) {
        mCvQuiz_.setVisibility(View.VISIBLE);
        Glide.with(this).load(Uri.parse(image.getImgUrl())).into(mIvQuiz_);
    }


    private void updateGraph(int scorePerc) {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(100 - scorePerc, "Unanswered"));
        entries.add(new PieEntry(scorePerc, "Answered"));
        PieDataSet set = new PieDataSet(entries, null);
        set.setValueFormatter(new GraphValueFormatter());
        set.setValueTextSize(30);
        set.setValueTextColor(Color.WHITE);
        set.setColors(Color.RED, Color.GREEN);
        set.setSliceSpace(4);
        PieData data = new PieData(set);
        mChart_.getDescription().setEnabled(false);
        mChart_.getLegend().setEnabled(false);
        mChart_.setData(data);
        mChart_.setCenterText("Score");
        mChart_.setCenterTextSize(15);
        mChart_.spin(2000, 0, 45, Easing.EasingOption.EaseInCirc);
        mChart_.invalidate();
    }

    static class GraphValueFormatter implements IValueFormatter {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return Math.round(value) + "%";
        }
    }
}
