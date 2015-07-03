package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * class to set Brightness effect to the image view
 */
public class BrightnessEffect {

    public static Bitmap getBrightnessEffect(Bitmap definedBitmap, int brightnessValue){

        // image size
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();
        // create output bitmap
        Bitmap brightnessAddedBitmap = Bitmap.createBitmap(width, height, definedBitmap.getConfig());
        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = definedBitmap.getPixel(x, y);
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
                brightnessAddedBitmap.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return  brightnessAddedBitmap;
    }
}
