package com.daqa.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.daqa.di.annotations.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class SPHelper implements ISPHelper {

    private final SharedPreferences mSharedPreferences_;

    private static final String APP_INITIALISED = "initialized";

    @Inject
    public SPHelper(@ApplicationContext Context context) {
        mSharedPreferences_ = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void setAppInitialized() {
        mSharedPreferences_.edit().putBoolean(APP_INITIALISED, true).apply();
    }

    @Override
    public boolean isAppInitialized() {
        return mSharedPreferences_.getBoolean(APP_INITIALISED, false);
    }
}
