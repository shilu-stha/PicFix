package com.hackathon.picfix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hackathon.picfix.utils.BlurBuilder;

import java.util.Random;

/**
 * Created by leapfrog on 7/3/15.
 */
public class PicFixImageView extends ImageView implements PicFixViewInterface {

    float blurRadius = 1;
    Drawable drawable;
    Context context;

    public PicFixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        drawable = getDrawable();
        this.context = context;
    }


    @Override
    public void setRotationTo(float rotationDegree) {
//        invalidate();
        Bitmap src = ((BitmapDrawable) this.getDrawable()).getBitmap();
        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(rotationDegree);

        // return new bitmap rotated using matrix
        this.setImageBitmap(Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true));
    }

    @Override
    public void setBlur(float radius) {
//        this.invalidate();

        if (radius < 1) {
            this.blurRadius = 1;
        } else {
            this.blurRadius = radius;
        }

        Bitmap bitmap_default = ((BitmapDrawable) this.getDrawable()).getBitmap();
        Bitmap blurredBitmap = BlurBuilder.blur(context, bitmap_default, this.blurRadius);
        this.setImageBitmap(blurredBitmap);

    }

    @Override
    public void setBrightness(int brightnessValue) {

        Bitmap src = ((BitmapDrawable) this.getDrawable()).getBitmap();
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // increase/decrease each channel
                R += brightnessValue;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }

                G += brightnessValue;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }

                B += brightnessValue;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }

                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        this.setImageBitmap(bmOut);
    }

    @Override
    public void setShading(int shadingColor) {

        Bitmap source = ((BitmapDrawable) this.getDrawable()).getBitmap();

        // get image size
        int width = source.getWidth();
        int height = source.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        source.getPixels(pixels, 0, width, 0, 0, width, height);

        int index = 0;
        // iteration through pixels
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // AND
                pixels[index] &= shadingColor;
            }
        }
        // output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
        this.setImageBitmap(bmOut);

    }

    @Override
    public void applyBlackFilter() {

        Bitmap source = ((BitmapDrawable) this.getDrawable()).getBitmap();

        // get image size
        int width = source.getWidth();
        int height = source.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        // random object
        Random random = new Random();

        int R, G, B, index = 0, thresHold = 0;
        // iteration through pixels
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // get color
                R = Color.red(pixels[index]);
                G = Color.green(pixels[index]);
                B = Color.blue(pixels[index]);
                // generate threshold
                thresHold = random.nextInt(500);
                if (R < thresHold && G < thresHold && B < thresHold) {
                    pixels[index] = Color.rgb(Color.parseColor("#000000"), Color.parseColor("#000000"), Color.parseColor("#000000"));
                }
            }
        }
        // output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
        this.setImageBitmap(bmOut);

    }

    @Override
    public void applyHueFilter(int hueLevel) {

        Bitmap source = ((BitmapDrawable) this.getDrawable()).getBitmap();

            // get image size
            int width = source.getWidth();
            int height = source.getHeight();
            int[] pixels = new int[width * height];
            float[] HSV = new float[3];
            // get pixel array from source
            source.getPixels(pixels, 0, width, 0, 0, width, height);

            int index = 0;
            // iteration through pixels
            for(int y = 0; y < height; ++y) {
                for(int x = 0; x < width; ++x) {
                    // get current index in 2D-matrix
                    index = y * width + x;
                    // convert to HSV
                    Color.colorToHSV(pixels[index], HSV);
                    // increase Saturation level
                    HSV[0] *= hueLevel;
                    HSV[0] = (float) Math.max(0.0, Math.min(HSV[0], 360.0));
                    // take color back
                    pixels[index] |= Color.HSVToColor(HSV);
                }
            }
            // output bitmap
            Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
            this.setImageBitmap(bmOut);

    }

    @Override
    public void applySaturationFilter(int saturationLevel) {

        Bitmap source = ((BitmapDrawable) this.getDrawable()).getBitmap();

        // get image size
        int width = source.getWidth();
        int height = source.getHeight();
        int[] pixels = new int[width * height];
        float[] HSV = new float[3];
        // get pixel array from source
        source.getPixels(pixels, 0, width, 0, 0, width, height);

        int index = 0;
        // iteration through pixels
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // convert to HSV
                Color.colorToHSV(pixels[index], HSV);
                // increase Saturation level
                HSV[1] *= saturationLevel;
                HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
                // take color back
                pixels[index] |= Color.HSVToColor(HSV);
            }
        }
        // output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
        this.setImageBitmap(bmOut);
    }
}
