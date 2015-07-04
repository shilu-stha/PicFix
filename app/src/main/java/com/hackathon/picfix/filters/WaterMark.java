package com.hackathon.picfix.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Set watermark to the bitmap
 *
 * @date 7/3/15
 */
public class WaterMark {

    /**
     * @param definedBitmap supplied bitmap
     * @param watermark     string to applied as watermark
     * @param location      location where watermark is to be set
     * @param color         color of the string to be set as watermark
     * @param alpha         alpha of the color to be applied
     * @param size          size of the string
     * @param underline     true if underline needed else false
     * @return watermarked bitmap
     */
    public static Bitmap getWaterMarked(Bitmap definedBitmap, String watermark, Point location, int color, int alpha, int size, boolean underline) {

        int w = definedBitmap.getWidth();
        int h = definedBitmap.getHeight();
        Bitmap waterMarkedBitmap = Bitmap.createBitmap(w, h, definedBitmap.getConfig());

        Canvas canvas = new Canvas(waterMarkedBitmap);
        canvas.drawBitmap(definedBitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(alpha);
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setUnderlineText(underline);
        canvas.drawText(watermark, location.x, location.y, paint);

        return waterMarkedBitmap;

    }
}
