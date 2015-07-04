package com.hackathon.picfix.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

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

    @InjectView(R.id.img_candidate)
    ImageView imgCandidate;
    String feature;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feature = getIntent().getExtras().getString(com.hackathon.picfix.utils.Constants.FEATURE);
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.person);
        setImageFeature();
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
                imgCandidate.setImageBitmap(RotationEffect.getRotatedBitmap(this, bitmap, 90));
                break;
            case "Blur":
                imgCandidate.setImageBitmap(BlurBuilder.blur(this, bitmap, 20));
                break;
            case "Brightness":
                imgCandidate.setImageBitmap(BrightnessEffect.getBrightnessEffect(bitmap, 20));
                break;
            case "Shading":
                imgCandidate.setImageBitmap(ShadingEffect.getShadingEffect(bitmap, Color.BLUE));
                break;
            case "Black Filter":
                imgCandidate.setImageBitmap(BlackFilter.getBlackFilteredImage(bitmap));
                break;
            case "Saturation Filter":
                imgCandidate.setImageBitmap(SaturationFilter.getSaturatedFilter(bitmap, 9));
                break;
            case "Snow Effect":
                imgCandidate.setImageBitmap(SnowEffect.getSnowEffectBitmap(bitmap));
                break;
            case "Flea Effect":
                imgCandidate.setImageBitmap(FleaEffect.getFleaEffectBitmap(bitmap));
                break;
            case "Tint":
                imgCandidate.setImageBitmap(TintImage.getTintImage(bitmap, 45));
                break;
            case "flip":
                imgCandidate.setImageBitmap(FlipImage.getFlippedImage(bitmap, com.hackathon.picfix.utils.Constants.FLIP_HORIZONTAL));
                break;
            case "WaterMark":
                //imgCandidate.setImageBitmap(WaterMark.getWaterMarked());
                break;
            case "Resize":
                startActivity(new Intent(DetailActivity.this, FrameActivity.class));
                break;
            case "Crop":
                break;
            case "sketch":
                imgCandidate.setImageBitmap(Sketch.changeToSketch(bitmap));
                break;
            case "Hue":
                imgCandidate.setImageBitmap(bitmap);
                break;
        }
    }
}
