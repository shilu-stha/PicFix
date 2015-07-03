package com.hackathon.picfix.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Point location=new Point();
        location.set(2000, 2000);

        PicFixImageView asd = (PicFixImageView) findViewById(R.id.ivTest);

        Button btn = (Button) findViewById(R.id.btnFrames);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), FrameActivity.class);
                startActivity(intent);
            }
        });

//        asd.setRotationTo(67);
//        asd.setBlur(25);
//        asd.setBrightness(80);
//        asd.setShading(Color.parseColor("#00aa32"));
//        asd.applyBlackFilter();/
//        asd.applyHueFilter(50);
        asd.setColorFilter(asd.applyHue(50));
//        asd.adjustHue(50);
//        asd.applySaturationFilter(80);
//        asd.applySnowEffect();
//        asd.applyFleaEffect();
//        asd.setTintImage(20);
//        asd.flipImage(Constants.FLIP_VERTICAL);
//        asd.sketch();
//        asd.setWaterMark("Hackathon", location, Color.BLACK, 10, 30, false);
    }
}
