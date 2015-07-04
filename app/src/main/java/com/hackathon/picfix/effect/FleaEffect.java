package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.hackathon.picfix.utils.Constants;

import java.util.Random;

/**
 * set Flea Effect in the bitmap
 */
public class FleaEffect {

    public static Bitmap getFleaEffectBitmap(Bitmap definedBitmap){
        // get image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        definedBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        // a random object
        Random random = new Random();

        int index = 0;
        // iteration through pixels
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // get random color
                int randColor = Color.rgb(random.nextInt(Constants.COLOR_MAX),
                        random.nextInt(Constants.COLOR_MAX), random.nextInt(Constants.COLOR_MAX));
                // OR
                pixels[index] |= randColor;
            }
        }
        // output bitmap
        Bitmap fleaEffectBitmap = Bitmap.createBitmap(width, height, definedBitmap.getConfig());
        fleaEffectBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return fleaEffectBitmap;
    }
}
