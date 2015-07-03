package com.hackathon.picfix.utils;

import android.graphics.Bitmap;

/**
 * Created by leapfrog on 7/3/15.
 */
public class ImageProcessor {

    Bitmap mImage;
    boolean mIsError = false;

    public ImageProcessor(final Bitmap image) {
        mImage = image.copy(image.getConfig(), image.isMutable());
        if(mImage == null) {
            mIsError = true;
        }
    }

    public boolean isError() {
        return mIsError;
    }

    public void setImage(final Bitmap image) {
        mImage = image.copy(image.getConfig(), image.isMutable());
        if(mImage == null) {
            mIsError = true;
        } else {
            mIsError = false;
        }
    }

    public Bitmap getImage() {
        if(mImage == null){
            return null;
        }
        return mImage.copy(mImage.getConfig(), mImage.isMutable());
    }

    public void free() {
        if(mImage != null && !mImage.isRecycled()) {
            mImage.recycle();
            mImage = null;
        }
    }


}
