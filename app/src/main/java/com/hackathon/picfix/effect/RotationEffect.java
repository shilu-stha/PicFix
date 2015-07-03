package com.hackathon.picfix.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by leapfrog on 7/3/15.
 */
public class RotationEffect {

    public static Bitmap getRotatedBitmap(Context context, Bitmap image, float rotationDegree){

        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(rotationDegree);

        // return new bitmap rotated using matrix
        Bitmap rotatedBitmap =  Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        return rotatedBitmap;
    }
}
