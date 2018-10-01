package com.daqa.quiz;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daqa.R;
import com.daqa.data.db.model.Option;
import com.daqa.data.db.model.QuizData;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private final OnOptionClickListener mListener_;
    private LayoutInflater mInflater_;
    private QuizData mQuizData_;

    QuizAdapter(final OnOptionClickListener listener, LayoutInflater inflater) {
        mInflater_ = inflater;
        mListener_ = new OnOptionClickListener() {
            @Override
            public void onClick(int optionPos) {
                mQuizData_.setAnswered();
                int optionId = mQuizData_.getOptions().get(optionPos).getOptionId();
                mQuizData_.setAnswer(optionId);
                notifyDataSetChanged();
                listener.onClick(optionId);
            }
        };
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuizViewHolder(mInflater_.inflate(R.layout.row_option, viewGroup, false), mListener_);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder viewHolder, int i) {
        viewHolder.bind(mQuizData_);
    }

    @Override
    public int getItemCount() {
        return mQuizData_ != null ? mQuizData_.getOptions().size() : 0;
    }

    public void setQuizData(QuizData quizData) {
        this.mQuizData_ = quizData;
        notifyDataSetChanged();
    }

    static class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTvOption_;
        private final OnOptionClickListener mListener_;

        QuizViewHolder(@NonNull View itemView, OnOptionClickListener listener) {
            super(itemView);
            this.mTvOption_ = itemView.findViewById(R.id.tv_option);
            itemView.setOnClickListener(this);
            this.mListener_ = listener;
        }

        @Override
        public void onClick(View view) {
            mListener_.onClick(getAdapterPosition());
        }

        void bind(QuizData quizData) {
            Option option = quizData.getOptions().get(getAdapterPosition());
            mTvOption_.setText(option.getValue());
            itemView.setBackgroundColor(quizData.isAnswered() ? (option.isCorrect() ? Color.GREEN : Color.RED) : ContextCompat.getColor(itemView.getContext(), R.color.color_option));
            itemView.setOnClickListener(quizData.isAnswered() ? null : this);

        }
    }

    interface OnOptionClickListener {
        void onClick(int optionPos);
    }
}
