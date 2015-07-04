package com.hackathon.picfix.interfaces;

import android.content.Context;
import android.graphics.Bitmap;

import com.hackathon.picfix.widgets.PicFixImageView;

/**
 * Created by shilu shrestha on 7/3/15.
 * all the methods used to set frame are defined here.
 */
public interface CustomFrameInterface {

    /**
     * Set resource location for custom frames to be used in the application
     *
     * @param frames - resource id list
     */
    void setFrames(Integer[] frames);

    /**
     * Return the frames used by the application
     *
     * @return the frame id list
     */
    Integer[] getFrames();

    /**
     * Set frames as overlay on top of the required image view.
     *
     * @param context - application context
     * @param frameImageView - {@link android.widget.ImageView} which contain selected frame
     * @param selectedImageView - {@link PicFixImageView} which contains bitmap
     */
    void createFrameOverlay(Context context, PicFixImageView frameImageView, PicFixImageView selectedImageView);

    /**
     * Set selected frame from adapter. Change the frames on top of ImageView accordingly.
     *
     * @param position - position of selected frame
     */
    void setSelectedFrame(int position);

    /**
     * Returns the final framed bitmap of the image.
     *
     *  @param selectedImageView - {@link PicFixImageView} which contains bitmap to set frame
     * @param position - position of selected frame
     */
    Bitmap getFramedBitmap(PicFixImageView selectedImageView, int position);
}
