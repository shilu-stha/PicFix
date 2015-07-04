package com.hackathon.picfix.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;
import com.hackathon.picfix.effect.BlurBuilder;
import com.hackathon.picfix.effect.BrightnessEffect;
import com.hackathon.picfix.effect.FleaEffect;
import com.hackathon.picfix.effect.FlipImage;
import com.hackathon.picfix.effect.RotationEffect;
import com.hackathon.picfix.effect.ShadingEffect;
import com.hackathon.picfix.effect.Sketch;
import com.hackathon.picfix.effect.SnowEffect;
import com.hackathon.picfix.effect.TintImage;
import com.hackathon.picfix.filters.BlackFilter;
import com.hackathon.picfix.filters.SaturationFilter;

import butterknife.InjectView;

public class DetailActivity extends BaseActivity {

    @InjectView(R.id.seek_bar)
    SeekBar seekBar;

    @InjectView(R.id.img_candidate)
    PicFixImageView imgCandidate;

    @InjectView(R.id.view_overlay)
    View view;

    String feature;
    Bitmap bitmap;

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feature = getIntent().getExtras().getString(com.hackathon.picfix.utils.Constants.FEATURE);
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.person);

        setImageFeature();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
                setImageFeature();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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

    private void setImageFeature() {
        switch (feature) {
            case "Rotate":
                imgCandidate.setRotationTo(value);
                break;
            case "Blur":
                if(value>0 && value<25) {
                    imgCandidate.setBlur(value);
                }
                break;
            case "Brightness":
                imgCandidate.setBrightness(value);
                break;
            case "Shading":
                imgCandidate.setShading(Color.BLUE);
                break;
            case "Black Filter":
                imgCandidate.applyBlackFilter();
                break;
            case "Saturation Filter":
                imgCandidate.applySaturationFilter(value);
                break;
            case "Snow Effect":
                imgCandidate.applySnowEffect();
                break;
            case "Flea Effect":
                imgCandidate.applyFleaEffect();
                break;
            case "Tint":
                imgCandidate.setTintImage(value);
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
                imgCandidate.applyHue(value);
                break;
            case "Frames":
                startActivity(new Intent(this, FrameActivity.class));
                break;
        }
    }


}
