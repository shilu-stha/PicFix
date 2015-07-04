package com.hackathon.picfix.effect;

import android.graphics.Bitmap;

import com.hackathon.picfix.utils.Constants;

/**
 *Provide tint effect to bitmap
 */
public class TintImage {

    /**
     * This method takes the RGB value of each pixel in the bitmap and then change them according to the tint degree supplied.
     *
     * @param definedBitmap supplied bitmap
     * @param tintDegree degree of tint value to be set
     * @return tint added bitmap
     */

    public static Bitmap getTintImage(Bitmap definedBitmap, int tintDegree){
        int width = definedBitmap.getWidth();
        int height = definedBitmap.getHeight();

        int[] pix = new int[width * height];
        definedBitmap.getPixels(pix, 0, width, 0, 0, width, height);

        int RY, GY, BY, RYY, GYY, BYY, R, G, B, Y;
        double angle = (Constants.PI * (double) tintDegree) / Constants.HALF_CIRCLE_DEGREE;

        int S = (int) (Constants.RANGE * Math.sin(angle));
        int C = (int) (Constants.RANGE * Math.cos(angle));

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                int r = (pix[index] >> 16) & 0xff;
                int g = (pix[index] >> 8) & 0xff;
                int b = pix[index] & 0xff;
                RY = (70 * r - 59 * g - 11 * b) / 100;
                GY = (-30 * r + 41 * g - 11 * b) / 100;
                BY = (-30 * r - 59 * g + 89 * b) / 100;
                Y = (30 * r + 59 * g + 11 * b) / 100;
                RYY = (S * BY + C * RY) / 256;
                BYY = (C * BY - S * RY) / 256;
                GYY = (-51 * RYY - 19 * BYY) / 100;
                R = Y + RYY;
                R = (R < 0) ? 0 : ((R > 255) ? 255 : R);
                G = Y + GYY;
                G = (G < 0) ? 0 : ((G > 255) ? 255 : G);
                B = Y + BYY;
                B = (B < 0) ? 0 : ((B > 255) ? 255 : B);
                pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
            }

        Bitmap tintedBitmap = Bitmap.createBitmap(width, height, definedBitmap.getConfig());
        tintedBitmap.setPixels(pix, 0, width, 0, 0, width, height);

        pix = null;

        return tintedBitmap;
    }
}
