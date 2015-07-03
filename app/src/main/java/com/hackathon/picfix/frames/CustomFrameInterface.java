package com.hackathon.picfix.frames;

import android.content.Context;
import android.graphics.Bitmap;

import com.hackathon.picfix.PicFixImageView;

/**
 * Created by shilushrestha on 7/3/15.
 */
public interface CustomFrameInterface {

    /**
     * Set resource location for custom frames to be used in the application
     *
     * @param frames
     */
    void setFrames(Integer[] frames);

    /**
     * Return the frames used by the application
     *
     * @return
     */
    Integer[] getFrames();

    /**
     * Set frames as overlay on top of the required image view.
     *
     * @param context
     * @param frameImageView
     * @param selectedImageView
     */
    void createFrameOverlay(Context context, PicFixImageView frameImageView, PicFixImageView selectedImageView);

    /**
     * Set selected frame from adapter. Change the frames on top of ImageView accordingly.
     *
     * @param position
     */
    void setSelectedFrame(int position);

    /**
     * Returns the final framed bitmap of the image.
     *
     *  @param selectedImageView
     * @param position
     */
    Bitmap getFramedBitmap(PicFixImageView selectedImageView, int position);
}
