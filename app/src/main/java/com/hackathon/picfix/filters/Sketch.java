package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.hackathon.picfix.data.SketchType;

/**
 * Convert bitmap into sketch image
 *
 * @author Manas
 * @date 7/4/2015
 */
public class Sketch {

    /**
     * Set the type of sketch you want to create
     *
     * @param bitmap supplied bitmap
     * @return sketched bitmap
     */
    public static final Bitmap changeToSketch(Bitmap bitmap) {
        return changeToSketch(bitmap, SketchType.COLOR_PENCIL_SKETCH.getType(), 250);
    }


    /**
     * Multiply each RGB value of the supplied bitmap to sketch type selected
     * and create a final value with added threshold.
     *
     * @param bitmap    supplied bitmap
     * @param type      {@link SketchType} value for sketch
     * @param threshold point to implement the sketch effect
     * @return sketched bitmap
     */
    public static Bitmap changeToSketch(Bitmap bitmap, int type, int threshold) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, bitmap.getConfig());

        int A, R, G, B;
        int sumR, sumG, sumB;
        int[][] pixels = new int[3][3];
        for (int y = 0; y < height - 2; ++y) {
            for (int x = 0; x < width - 2; ++x) {
                //      get pixel matrix
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        pixels[i][j] = bitmap.getPixel(x + i, y + j);
                    }
                }
                // get alpha of center pixel
                A = Color.alpha(pixels[1][1]);
                // init color sum
                sumR = sumG = sumB = 0;
                sumR = (type * Color.red(pixels[1][1])) - Color.red(pixels[0][0]) - Color.red(pixels[0][2]) - Color.red(pixels[2][0]) - Color.red(pixels[2][2]);
                sumG = (type * Color.green(pixels[1][1])) - Color.green(pixels[0][0]) - Color.green(pixels[0][2]) - Color.green(pixels[2][0]) - Color.green(pixels[2][2]);
                sumB = (type * Color.blue(pixels[1][1])) - Color.blue(pixels[0][0]) - Color.blue(pixels[0][2]) - Color.blue(pixels[2][0]) - Color.blue(pixels[2][2]);
                // get final Red
                R = sumR + threshold;
                if (R < 0) {
                    R = 0;
                } else if (R > 255) {
                    R = 255;
                }
                // get final Green
                G = sumG + threshold;
                if (G < 0) {
                    G = 0;
                } else if (G > 255) {
                    G = 255;
                }
                // get final Blue
                B = sumB + threshold;
                if (B < 0) {
                    B = 0;
                } else if (B > 255) {
                    B = 255;
                }

                result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
            }
        }
        return result;
    }


}