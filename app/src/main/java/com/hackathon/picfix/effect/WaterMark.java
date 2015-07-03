package com.hackathon.picfix.effect;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Class to set watermark to the bitmap
 */
public class WaterMark {

    public static Bitmap getWaterMarked(Bitmap definedBitmap,String watermark, Point location, int color, int alpha, int size, boolean underline){

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
