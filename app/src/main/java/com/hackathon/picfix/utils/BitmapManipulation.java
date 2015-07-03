package com.hackathon.picfix.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by shilushrestha on 7/3/15.
 */
public class BitmapManipulation {
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

    public static Bitmap overlapBitmaps(Bitmap bitmapSelectedPicture, Bitmap bitmapOverlayPicture) {
        Bitmap bmOverlay = Bitmap.createBitmap(bitmapOverlayPicture.getWidth(),
                bitmapOverlayPicture.getHeight(), bitmapOverlayPicture.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bitmapSelectedPicture, new Matrix(), null);
        canvas.drawBitmap(bitmapOverlayPicture, new Matrix(), null);
        return bmOverlay;
    }
}
