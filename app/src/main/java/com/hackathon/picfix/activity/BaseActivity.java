package com.hackathon.picfix.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * Abstract class created to ease the calling of setContentView value
 * and View Injection part. ButterKnife is used for view injection.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(getLayout());
        //Inject the view
        ButterKnife.inject(this);
    }

    public abstract int getLayout();
}
