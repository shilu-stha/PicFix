package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

/**
 * Provides Hue Filter effect to the bitmap created
 */
public class HueFilter {

    public static Bitmap getHuedBitmap(Bitmap definedBitmap, int hueLevel) {
        // get image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        int[] pixels = new int[width * height];
        float[] HSV = new float[3];
        // get pixel array from source
        definedBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int index = 0;
        // iteration through pixels
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
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
        Bitmap huedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        huedBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return huedBitmap;
    }

    /**
     * Creates a HUE ajustment ColorFilter
     *
     * @param value degrees to shift the hue.
     * @return
     * @see http://groups.google.com/group/android-developers/browse_thread/thread/9e215c83c3819953
     * @see http://gskinner.com/blog/archives/2007/12/colormatrix_cla.html
     */
    public static ColorFilter adjustHue(float value) {
        ColorMatrix cm = new ColorMatrix();

        adjustHue(cm, value);

        return new ColorMatrixColorFilter(cm);
    }

    /**
     * @param cm
     * @param value
     * @see http://groups.google.com/group/android-developers/browse_thread/thread/9e215c83c3819953
     * @see http://gskinner.com/blog/archives/2007/12/colormatrix_cla.html
     */
    public static void adjustHue(ColorMatrix cm, float value) {
        value = cleanValue(value, 180f) / 180f * (float) Math.PI;
        if (value == 0) {
            return;
        }
        float cosVal = (float) Math.cos(value);
        float sinVal = (float) Math.sin(value);
        float lumR = 0.213f;
        float lumG = 0.715f;
        float lumB = 0.072f;
        float[] mat = new float[]
                {
                        lumR + cosVal * (1 - lumR) + sinVal * (-lumR), lumG + cosVal * (-lumG) + sinVal * (-lumG), lumB + cosVal * (-lumB) + sinVal * (1 - lumB), 0, 0,
                        lumR + cosVal * (-lumR) + sinVal * (0.143f), lumG + cosVal * (1 - lumG) + sinVal * (0.140f), lumB + cosVal * (-lumB) + sinVal * (-0.283f), 0, 0,
                        lumR + cosVal * (-lumR) + sinVal * (-(1 - lumR)), lumG + cosVal * (-lumG) + sinVal * (lumG), lumB + cosVal * (1 - lumB) + sinVal * (lumB), 0, 0,
                        0f, 0f, 0f, 1f, 0f,
                        0f, 0f, 0f, 0f, 1f};
        cm.postConcat(new ColorMatrix(mat));
    }

    protected static float cleanValue(float p_val, float p_limit) {
        return Math.min(p_limit, Math.max(-p_limit, p_val));
    }
}
