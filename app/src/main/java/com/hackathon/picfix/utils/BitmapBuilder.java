package com.hackathon.picfix.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Manipulate bitmap to calculate sample size, decode bitmaps
 *
 * @author shilushrestha
 * @date 7/3/15
 */
public class BitmapBuilder {

    /**
     * Decode supplied bitmap and load efficiently in memory
     *
     * @param resources {@link Resources} location for the bitmap
     * @param mFrame    id of the bitmap
     * @return sampled bitmap
     */
    public static Bitmap decodeSampledBitmapFromResourcePreview(Resources resources, Integer mFrame) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, mFrame, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,
                options.outWidth / 2, options.outHeight / 2);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, mFrame, options);
    }

    /**
     * Used to handle large bitmaps efficiently. Load smaller bitmap in memory.
     *
     * @param options   {@link android.graphics.BitmapFactory.Options} objects of the supplied bitmap
     * @param reqWidth  the width to scale bitmap to
     * @param reqHeight height to scale bitmap to
     * @return sampled bitmap
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        int heightRatio = (int) Math.ceil(height / (float) reqHeight);
        int widthRatio = (int) Math.ceil(width / (float) reqWidth);
        if (height > reqHeight || width > reqWidth) {
            if (heightRatio > widthRatio) {
                inSampleSize = heightRatio;
            } else {
                inSampleSize = widthRatio;
            }
        }

        return inSampleSize;
    }

    /**
     * Combine two bitmaps to create a single bitmap
     *
     * @param bitmapSelectedPicture selected Image bitmap
     * @param bitmapOverlayPicture  frame bitmap
     * @return combined bitmap
     */
    public static Bitmap overlapBitmaps(Bitmap bitmapSelectedPicture, Bitmap bitmapOverlayPicture) {
        Bitmap bmOverlay = Bitmap.createBitmap(bitmapOverlayPicture.getWidth(),
                bitmapOverlayPicture.getHeight(), bitmapOverlayPicture.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bitmapSelectedPicture, new Matrix(), null);
        canvas.drawBitmap(bitmapOverlayPicture, new Matrix(), null);
        return bmOverlay;
    }

    /**
     * Decode supplied bitmap and load efficiently in memory
     *
     * @param resources {@link Resources} location for the bitmap
     * @param resId     id of the bitmap
     * @param reqWidth  required width of new bitmap
     * @param reqHeight required height of new bitmap
     * @return sampled bitmap
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources resources, Integer resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }
}
