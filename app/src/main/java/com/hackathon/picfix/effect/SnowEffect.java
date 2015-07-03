package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.hackathon.picfix.utils.Constants;

import java.util.Random;

/**
 * Created by leapfrog on 7/3/15.
 */
public class SnowEffect {

    public static Bitmap getSnowEffectBitmap (Bitmap definedBitmap){
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
                if (R > thresHold && G > thresHold && B > thresHold) {
                    pixels[index] = Color.rgb(Constants.COLOR_MAX, Constants.COLOR_MAX, Constants.COLOR_MAX);
                }
            }
        }
        // output bitmap
        Bitmap snowEffectBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        snowEffectBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return snowEffectBitmap;
    }
}
