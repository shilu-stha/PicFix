package com.hackathon.picfix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hackathon.picfix.effect.BlurBuilder;
import com.hackathon.picfix.effect.BrightnessEffect;
import com.hackathon.picfix.effect.FleaEffect;
import com.hackathon.picfix.effect.FlipImage;
import com.hackathon.picfix.effect.RotationEffect;
import com.hackathon.picfix.effect.ShadingEffect;
import com.hackathon.picfix.effect.Sketch;
import com.hackathon.picfix.effect.SnowEffect;
import com.hackathon.picfix.effect.TintImage;
import com.hackathon.picfix.effect.WaterMark;
import com.hackathon.picfix.filters.BlackFilter;
import com.hackathon.picfix.filters.HueFilter;
import com.hackathon.picfix.filters.SaturationFilter;
import com.hackathon.picfix.utils.Constants;

import java.util.Random;

/**
 * Created by leapfrog on 7/3/15.
 */
public class PicFixImageView extends ImageView implements PicFixViewInterface {

    float blurRadius = 1;
    Drawable drawable;
    Context context;
    Bitmap definedBitmap;

    public PicFixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        drawable = getDrawable();
        this.context = context;
        definedBitmap = ((BitmapDrawable) this.getDrawable()).getBitmap();
    }


    @Override
    public void setRotationTo(float rotationDegree) {
        Bitmap rotatedBitmap = RotationEffect.getRotatedBitmap(context, definedBitmap, rotationDegree);
        this.setImageBitmap(rotatedBitmap);
    }

    @Override
    public void setBlur(float radius) {

        if (radius < 1) {
            this.blurRadius = 1;
        } else {
            this.blurRadius = radius;
        }
        Bitmap blurredBitmap = BlurBuilder.blur(context, definedBitmap, this.blurRadius);
        this.setImageBitmap(blurredBitmap);

    }

    @Override
    public void setBrightness(int brightnessValue) {
        Bitmap brightBitmap = BrightnessEffect.getBrightnessEffect(definedBitmap, brightnessValue);
        // return final image
        this.setImageBitmap(brightBitmap);
    }

    @Override
    public void setShading(int shadingColor) {

        Bitmap shadedBitmap = ShadingEffect.getShadingEffect(definedBitmap, shadingColor);
        this.setImageBitmap(shadedBitmap);

    }

    @Override
    public void applyBlackFilter() {

        Bitmap blackFiltered = BlackFilter.getBlackFilteredImage(definedBitmap);
        this.setImageBitmap(blackFiltered);

    }

    @Override
    public void applyHueFilter(int hueLevel) {

        Bitmap huedBitmap = HueFilter.getHuedBitmap(definedBitmap, hueLevel);
        this.setImageBitmap(huedBitmap);

    }

    @Override
    public void applySaturationFilter(int saturationLevel) {

        Bitmap saturatedBitmap = SaturationFilter.getSaturatedFilter(definedBitmap, saturationLevel);
        this.setImageBitmap(saturatedBitmap);
    }

    @Override
    public void applySnowEffect() {
        Bitmap snowEffectBitmap = SnowEffect.getSnowEffectBitmap(definedBitmap);
        this.setImageBitmap(snowEffectBitmap);

    }

    @Override
    public void applyFleaEffect() {
        Bitmap fleaEffectBitmap = FleaEffect.getFleaEffectBitmap(definedBitmap);
        this.setImageBitmap(fleaEffectBitmap);

    }

    @Override
    public void setTintImage(int tintDegree) {

        Bitmap tintedBitmap = TintImage.getTintImage(definedBitmap, tintDegree);
        this.setImageBitmap(tintedBitmap);
    }

    @Override
    public void flipImage(int flipType) {
        Bitmap flippedBitmap = FlipImage.getFlipedImage(definedBitmap, flipType);
        this.setImageBitmap(flippedBitmap);
    }

    @Override
    public void setWaterMark(String watermark, Point location, int color, int alpha, int size, boolean underline) {

        Bitmap waterMarkedBitmap = WaterMark.getWaterMarked(definedBitmap, watermark, location, color, alpha, size, underline);
        this.setImageBitmap(waterMarkedBitmap);
    }

    @Override
    public void resizeImage(int width, int height) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(definedBitmap, width, height, true);

        this.setImageBitmap(resizedBitmap);
    }

    @Override
    public void doCrop(float startX, float startY, int width, int height) {
        Bitmap resizedbitmap1 = Bitmap.createBitmap(definedBitmap, (int) startX, (int) startY, width, height);

        this.setImageBitmap(resizedbitmap1);
    }

    @Override
    public void sketch(int type, int threshold) {
        this.setImageBitmap(Sketch.changeToSketch(definedBitmap, type, threshold));
    }
}
