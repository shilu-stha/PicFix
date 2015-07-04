package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * provide rotated effect to the bitmap
 */
public class RotationEffect {

    /**
     * Rotates the given bitmap image with the rotation degree supplied.
     * Changes the matrix of the bitmap for rotation.
     *
     * @param image          supplied bitmap
     * @param rotationDegree rotation degree can be <b>0-360</b>
     * @return
     */
    public static Bitmap getRotatedBitmap(Bitmap image, float rotationDegree) {

        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(rotationDegree);

        // return new bitmap rotated using matrix

        return Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
    }
}
