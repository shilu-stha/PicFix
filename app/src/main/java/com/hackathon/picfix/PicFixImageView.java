package com.hackathon.picfix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hackathon.picfix.effect.BlurBuilder;
import com.hackathon.picfix.effect.BrightnessEffect;
import com.hackathon.picfix.effect.FleaEffect;
import com.hackathon.picfix.effect.FlipImage;
import com.hackathon.picfix.effect.RotationEffect;
import com.hackathon.picfix.effect.ShadingEffect;
import com.hackathon.picfix.effect.SnowEffect;
import com.hackathon.picfix.effect.TintImage;
import com.hackathon.picfix.effect.WaterMark;
import com.hackathon.picfix.filters.BlackFilter;
import com.hackathon.picfix.filters.HueFilter;
import com.hackathon.picfix.filters.SaturationFilter;
import com.hackathon.picfix.frames.CustomFrameInterface;
import com.hackathon.picfix.utils.BitmapManipulation;

import java.io.ByteArrayOutputStream;

/**
 * Created by leapfrog on 7/3/15.
 */
public class PicFixImageView extends ImageView implements PicFixViewInterface, CustomFrameInterface {

    float blurRadius = 1;
    Drawable drawable;
    Context context;
    Bitmap definedBitmap;
    private Integer[] mFrames;
    private RelativeLayout.LayoutParams mOverlayParams;
    private PicFixImageView imgOverlayFrame;

    public PicFixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        drawable = getDrawable();
        this.context = context;
        definedBitmap = ((BitmapDrawable) this.getDrawable()).getBitmap();
    }


    @Override
    public void setRotationTo(float rotationDegree) {
//        invalidate();
        Bitmap rotatedBitmap = RotationEffect.getRotatedBitmap(context, definedBitmap, rotationDegree);
        this.setImageBitmap(rotatedBitmap);

    }

    @Override
    public void setBlur(float radius) {
//        this.invalidate();

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
    public void setFrames(Integer[] framesId) {
        mFrames = framesId;
    }

    @Override
    public Integer[] getFrames() {
        return mFrames;
    }

    @Override
    public void createFrameOverlay(Context context, PicFixImageView frameImageView, PicFixImageView selectedImageView) {
//        mContext = context;
        imgOverlayFrame = frameImageView;
        mOverlayParams = new RelativeLayout.LayoutParams(selectedImageView.getWidth(), selectedImageView.getHeight());

        mOverlayParams.addRule(RelativeLayout.ALIGN_LEFT, selectedImageView.getId());
        mOverlayParams.addRule(RelativeLayout.ALIGN_RIGHT, selectedImageView.getId());
        mOverlayParams.addRule(RelativeLayout.ALIGN_TOP, selectedImageView.getId());
        mOverlayParams.addRule(RelativeLayout.ALIGN_BOTTOM, selectedImageView.getId());
        mOverlayParams.addRule(RelativeLayout.ABOVE, selectedImageView.getId());

        imgOverlayFrame.setImageResource( mFrames[0]);
        imgOverlayFrame.setScaleType(ScaleType.FIT_XY);
        imgOverlayFrame.setLayoutParams(mOverlayParams);

    }

    @Override
    public void setSelectedFrame(int position) {
        imgOverlayFrame.setImageBitmap(BitmapManipulation.decodeSampledBitmapFromResourcePreview(
                context.getResources(), mFrames[position]));
        imgOverlayFrame.setScaleType(ImageView.ScaleType.FIT_XY);
        imgOverlayFrame.setLayoutParams(mOverlayParams);

    }

    @Override
    public Bitmap getFramedBitmap(PicFixImageView selectedImageView, int position) {
            ByteArrayOutputStream bytesOfImage = new ByteArrayOutputStream();
            // compressing to 10 percent

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 2;

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;

            Bitmap bitmapOverlayPicture = BitmapFactory.decodeResource(
                    getResources(), mFrames[position], options);

            Bitmap bitmapSelectedPicture = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();

            bitmapOverlayPicture = bitmapOverlayPicture.createScaledBitmap(bitmapOverlayPicture, bitmapSelectedPicture.getWidth(), bitmapSelectedPicture.getHeight(), true);

            Bitmap bitmap = BitmapManipulation.overlapBitmaps(bitmapSelectedPicture, bitmapOverlayPicture);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesOfImage);

        return bitmap;
    }

    public ColorFilter applyHue(int i) {
        return HueFilter.adjustHue(i);
    }
}
