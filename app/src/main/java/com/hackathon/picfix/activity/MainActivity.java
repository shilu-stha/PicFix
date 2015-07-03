package com.hackathon.picfix.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;


public class MainActivity extends ActionBarActivity {

    PicFixImageView asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asd = (PicFixImageView) findViewById(R.id.ivTest);

//        asd.setRotationTo(67);
//        asd.setBlur(25);
//        asd.setBrightness(-80);
//        asd.setShading(Color.parseColor("#00aa32"));

//        asd.applyBlackFilter();
//        asd.applyHueFilter(50);
        asd.applySaturationFilter(80);
    }
}
