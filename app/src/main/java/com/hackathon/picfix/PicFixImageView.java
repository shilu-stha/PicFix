package com.hackathon.picfix;

import android.content.Context;
import android.graphics.Bitmap;
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

/**
 *
 * extends {@link ImageView} class to apply all the image editor function
 * {@link PicFixViewInterface} is implemented to pass all the editor function
 */
public class PicFixImageView extends ImageView implements PicFixViewInterface {

    private float blurRadius = 1;
    private final Drawable drawable;
    private final Context context;
    private final Bitmap definedBitmap;

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
        Bitmap flippedBitmap = FlipImage.getFlippedImage(definedBitmap, flipType);
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
        Bitmap resizedBitmap = Bitmap.createBitmap(definedBitmap, (int) startX, (int) startY, width, height);

        this.setImageBitmap(resizedBitmap);
    }

    @Override
    public void sketch(int type, int threshold) {
        this.setImageBitmap(Sketch.changeToSketch(definedBitmap, type, threshold));
    }
}
