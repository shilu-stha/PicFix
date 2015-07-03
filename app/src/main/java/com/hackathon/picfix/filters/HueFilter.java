package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by leapfrog on 7/3/15.
 */
public class HueFilter {

    public static Bitmap getHuedBitmap(Bitmap definedBitmap,int hueLevel ){
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
}
