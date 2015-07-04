package com.hackathon.picfix.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * provide rotated effect to the bitmap
 */
public class RotationEffect {

    public static Bitmap getRotatedBitmap(Context context, Bitmap image, float rotationDegree){

        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(rotationDegree);

        // return new bitmap rotated using matrix

        return Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
    }
}
