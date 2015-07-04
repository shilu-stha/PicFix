package com.hackathon.picfix.filters;

import android.graphics.Bitmap;

/**
 * Provides shading effect of supplied color over bitmap
 *
 * @date 7/3/15
 */
public class Shading {

    /**
     * Updates each pixel of the bitmap with the supplied shading color.
     *
     * @param definedBitmap supplied bitmap
     * @param shadingColor  hex code of shading color
     * @return shaded bitmap
     */
    public static Bitmap getShadingEffect(Bitmap definedBitmap, int shadingColor) {
        // get image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        definedBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

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
        return bmOut;

    }
}
