package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Provide saturation effect to the bitmap
 */
public class SaturationFilter {

    public static Bitmap getSaturatedFilter(Bitmap definedBitmap, int saturationLevel){
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
                HSV[1] *= saturationLevel;
                HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
                // take color back
                pixels[index] |= Color.HSVToColor(HSV);
            }
        }
        // output bitmap
        Bitmap saturatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        saturatedBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return saturatedBitmap;
    }
}
