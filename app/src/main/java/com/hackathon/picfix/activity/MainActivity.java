package com.hackathon.picfix.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;
import com.hackathon.picfix.utils.Constants;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Point location=new Point();
        location.set(2000, 2000);

        PicFixImageView asd = (PicFixImageView) findViewById(R.id.ivTest);

//        asd.setRotationTo(67);
//        asd.setBlur(25);
//        asd.setBrightness(80);
//        asd.setShading(Color.parseColor("#00aa32"));
//        asd.applyBlackFilter();/
//        asd.applyHueFilter(50);
//        asd.applySaturationFilter(80);
//        asd.applySnowEffect();
//        asd.applyFleaEffect();
//        asd.setTintImage(20);
//        asd.flipImage(Constants.FLIP_VERTICAL);
//        asd.sketch();
//        asd.setWaterMark("Hackathon", location, Color.BLACK, 10, 30, false);
    }
}
