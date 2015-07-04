package com.hackathon.picfix.transform;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.hackathon.picfix.data.Constants;

/**
 * Flip the bitmap
 *
 * @date 7/3/15
 */
public class FlipImage {

    /**
     * This method flips the supplied bitmap according to the flip type supplied changing the {@link Matrix} of the image
     *
     * @param definedBitmap supplied bitmap to be change
     * @param flipType      constant to rotate the image with. It could be <i>Constants.FLIP_VERTICAL</i> and <i>Constants.FLIP_HORIZONTAL</i>
     * @return flipped bitmap
     */
    public static Bitmap getFlippedImage(Bitmap definedBitmap, int flipType) {

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

        return Bitmap.createBitmap(definedBitmap, 0, 0, definedBitmap.getWidth(), definedBitmap.getHeight(), matrix, true);
    }
}
