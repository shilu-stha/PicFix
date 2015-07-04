package com.hackathon.picfix.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;
import butterknife.InjectView;

/**
 * handle all the effect applied to the image view and pass it to respective method to process it.
 * */
public class DetailActivity extends BaseActivity {

    @InjectView(R.id.img_candidate)
    PicFixImageView imgCandidate;

    @InjectView(R.id.view_overlay)
    View view;

    private String feature;
    private Bitmap bitmap;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feature = getIntent().getExtras().getString(com.hackathon.picfix.utils.Constants.FEATURE);
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.person);

        final Runnable r = new Runnable() {
            public void run() {
                setImageFeature();
                view.setVisibility(View.GONE);
            }
        };
        handler.postDelayed(r, 1000);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//switch to the required method
    private void setImageFeature() {
        switch (feature) {
            case "Rotate":
                imgCandidate.setRotationTo(70);
                break;
            case "Blur":
                imgCandidate.setBlur(20);
                break;
            case "Brightness":
                imgCandidate.setBrightness(20);
                break;
            case "Shading":
                imgCandidate.setShading(Color.BLUE);
                break;
            case "Black Filter":
                imgCandidate.applyBlackFilter();
                break;
            case "Saturation Filter":
                imgCandidate.applySaturationFilter(9);
                break;
            case "Snow Effect":
                imgCandidate.applySnowEffect();
                break;
            case "Flea Effect":
                imgCandidate.applyFleaEffect();
                break;
            case "Tint":
                imgCandidate.setTintImage(45);
                break;
            case "flip":
                imgCandidate.flipImage(com.hackathon.picfix.utils.Constants.FLIP_HORIZONTAL);
                break;
            case "WaterMark":
                //imgCandidate.setImageBitmap(WaterMark.getWaterMarked());
                break;
            case "Resize":
                startActivity(new Intent(DetailActivity.this, ResizeActivity.class));
                break;
            case "Crop":
                break;
            case "sketch":
                imgCandidate.sketch(4, 250);
                break;
            case "Hue":
                imgCandidate.applyHue(20);
                break;
            case "Frames":
                startActivity(new Intent(this, FrameActivity.class));
                break;
        }
    }


}
