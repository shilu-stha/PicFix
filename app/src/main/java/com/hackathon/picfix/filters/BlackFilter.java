package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.hackathon.picfix.data.Constants;

import java.util.Random;

/**
 * Apply black filter to provided bitmap
 *
 * @date 7/3/15
 */
public class BlackFilter {

    /**
     *
     * @param definedBitmap
     * @return
     */
    public static Bitmap getBlackFilteredImage(Bitmap definedBitmap) {

        // get image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        int[] pixels = new int[width * height];

        // get pixel array from source
        definedBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        // random object
        Random random = new Random();

        int R, G, B, index = 0, threshold = 0;

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
                threshold = random.nextInt(Constants.COLOR_MAX);
                if (R < threshold && G < threshold && B < threshold) {
                    pixels[index] = Color.rgb(Constants.COLOR_MIN, Constants.COLOR_MIN, Constants.COLOR_MIN);
                }
            }
        }
        // output bitmap
        Bitmap blackFilteredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        blackFilteredBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return blackFilteredBitmap;

    }
}
