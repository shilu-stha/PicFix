package com.hackathon.picfix;

import android.app.Application;
import android.content.Context;


/**
 * Main Application responsible for static context to be used by anywhere in the class
 */
public class PicFixApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
