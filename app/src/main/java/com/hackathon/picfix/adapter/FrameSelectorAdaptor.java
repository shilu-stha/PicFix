package com.hackathon.picfix.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.hackathon.picfix.utils.BitmapBuilder;

/**
 * Adapter to set frames
 *
 * @author shilushrestha
 * @date 7/3/15
 */
public class FrameSelectorAdaptor extends BaseAdapter {
    private Context mContext;
    private Integer[] mFrames;


    public FrameSelectorAdaptor(Context context, Integer[] frames) {
        mContext = context;
        mFrames = frames;
    }

    public int getCount() {
        return mFrames.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int index, View view, ViewGroup viewGroup) {
        ImageView i = new ImageView(mContext);
        i.setImageBitmap(BitmapBuilder.decodeSampledBitmapFromResource(
                mContext.getResources(), mFrames[index], 100,
                100));
        i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        i.setScaleType(ImageView.ScaleType.FIT_XY);

        return i;
    }

}
