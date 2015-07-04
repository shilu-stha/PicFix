package com.hackathon.picfix.filters;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.hackathon.picfix.data.Constants;

/**
 * Set Blur effect
 *
 * @author Manas
 * @date 7/3/15
 */
public class Blur {

    /**
     * @param context
     * @param bitmap  supplied bitmap
     * @param radius  supplied radius of blur effect
     * @return blurred bitmap
     */
    public static Bitmap blur(Context context, Bitmap bitmap, float radius) {
        int width = Math.round(bitmap.getWidth() * Constants.BITMAP_SCALE);
        int height = Math.round(bitmap.getHeight() * Constants.BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

}