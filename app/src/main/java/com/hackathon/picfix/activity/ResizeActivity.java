package com.hackathon.picfix.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;

/**
 * Created by Manas on 7/4/2015.
 */
public class ResizeActivity extends Activity implements View.OnTouchListener, View.OnLongClickListener {

    PicFixImageView ivSample;
    ImageView ivRight;
    ImageView ivBottom;
    ImageView ivLeft;
    ImageView ivTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resize_activity);

        ivSample = (PicFixImageView) findViewById(R.id.iv_sample_image_resize);
        ivRight = (ImageView) findViewById(R.id.iv_scale_arrow_right);
        ivBottom = (ImageView) findViewById(R.id.iv_scale_arrow_bottom);
        ivLeft = (ImageView) findViewById(R.id.iv_scale_arrow_left);
        ivTop = (ImageView) findViewById(R.id.iv_scale_arrow_top);

        setListners();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getAction();

        switch (v.getId()) {
            case R.id.iv_scale_arrow_right:
                resizeImageWidth(action, event);
                break;
            case R.id.iv_scale_arrow_left:
                resizeImageWidth(action, event);
                break;
            case R.id.iv_scale_arrow_bottom:
                resizeImageHeight(action, event);
                break;
            case R.id.iv_scale_arrow_top:
                resizeImageHeight(action, event);
                break;
        }

        return true;
    }

    /**
     * set onTouchListeners for arrows
     */
    private void setListners() {
        ivBottom.setOnTouchListener(this);
        ivTop.setOnTouchListener(this);
        ivLeft.setOnTouchListener(this);
        ivRight.setOnTouchListener(this);
        ivSample.setOnLongClickListener(this);
    }

    /**
     * resize the image width by calling resizeImage
     *
     * @param action
     * @param event
     */
    private void resizeImageWidth(int action, MotionEvent event) {
        int newWidth = (int) (ivSample.getWidth() + event.getX());
        switch (action & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_MOVE: {
                if (newWidth > 0) {
                    ivSample.resizeImage(newWidth, ivSample.getHeight());
                }
                break;
            }

        }
    }

    /**
     * resize the image height by calling resizeImage
     *
     * @param action
     * @param event
     */
    private void resizeImageHeight(int action, MotionEvent event) {
        int newHeight = (int) (ivSample.getHeight() + event.getY());
        switch (action & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_MOVE: {
                if (newHeight > 0) {
                    ivSample.resizeImage(ivSample.getWidth(), newHeight);
                }
                break;
            }

        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sample_image_resize:
                toggleVisibility();
                break;
        }

        return true;
    }

    /**
     * make arrows visible if its invisible else make it visible
     */
    public void toggleVisibility() {
        if (ivBottom.getVisibility() == View.VISIBLE) {
            ivBottom.setVisibility(View.GONE);
            ivTop.setVisibility(View.GONE);
            ivLeft.setVisibility(View.GONE);
            ivRight.setVisibility(View.GONE);
        } else {
            ivBottom.setVisibility(View.VISIBLE);
            ivTop.setVisibility(View.VISIBLE);
            ivLeft.setVisibility(View.VISIBLE);
            ivRight.setVisibility(View.VISIBLE);
        }
    }
}
