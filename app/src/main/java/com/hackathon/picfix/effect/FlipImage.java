package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.hackathon.picfix.utils.Constants;

/**
 * Class ti flip the bitmap
 */
public class FlipImage {

    public static Bitmap getFlippedImage(Bitmap definedBitmap, int flipType){
        // create new matrix for transformation
        Matrix matrix = new Matrix();
        // if vertical
        if (flipType == Constants.FLIP_VERTICAL) {
            // y = y * -1
            matrix.preScale(1.0f, -1.0f);
        }
        // if horizontal
        else if (flipType == Constants.FLIP_HORIZONTAL) {
            // x = x * -1
            matrix.preScale(-1.0f, 1.0f);
            // unknown type
        } else {
            return null;
        }

        // return transformed image
//        Bitmap flippedBitmap =;

        return  Bitmap.createBitmap(definedBitmap, 0, 0, definedBitmap.getWidth(), definedBitmap.getHeight(), matrix, true);
    }
}
