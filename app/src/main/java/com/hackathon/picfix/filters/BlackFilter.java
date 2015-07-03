package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.hackathon.picfix.utils.Constants;

import java.util.Random;

/**
 * Created by leapfrog on 7/3/15.
 */
public class BlackFilter {

    public static Bitmap getBlackFilteredImage(Bitmap definedBitmap){

        // get image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        definedBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
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
                thresHold = random.nextInt(Constants.COLOR_MAX);
                if (R < thresHold && G < thresHold && B < thresHold) {
                    pixels[index] = Color.rgb(Constants.COLOR_MIN, Constants.COLOR_MIN, Constants.COLOR_MIN);
                }
            }
        }
        // output bitmap
        Bitmap blackFikteredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        blackFikteredBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return blackFikteredBitmap;

    }
}
