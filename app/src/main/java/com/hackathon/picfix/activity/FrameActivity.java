package com.hackathon.picfix.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;
import com.hackathon.picfix.frames.FrameSelectorAdaptor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FrameActivity extends ActionBarActivity {

    private final Integer[] mImageIds = {R.drawable.image1, R.drawable.image3};
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        final PicFixImageView imageView = (PicFixImageView) findViewById(R.id.overlayselected);
        final PicFixImageView imageViewFrame = (PicFixImageView) findViewById(R.id.overlayFrame);
        imageView.setFrames(mImageIds);
        imageView.createFrameOverlay(this, imageViewFrame, imageView);

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setSpacing(50);
        gallery.setAdapter(new FrameSelectorAdaptor(this, mImageIds));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mPosition = position;
                imageView.setSelectedFrame(position);


            }
        });

        Button btn = (Button) findViewById(R.id.btnDone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = imageView.getFramedBitmap(imageView, mPosition);

                setFrameSave("sample", "framed", bitmap);
            }
        });
    }

    /**
     *
     * @param fileName - name of the file
     * @param fileLocation -  file location
     * @param bitmap - bitmap to store
     * @return - file path
     */
    private String setFrameSave(String fileName, String fileLocation, Bitmap bitmap) {
        File file = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileLocation);
        if(!file.exists()) {
            file.mkdirs();
        }
        File bitmapFile = new File(file, fileName
                + System.currentTimeMillis() + ".jpeg");
        FileOutputStream out;
        try {
            ByteArrayOutputStream bytesOfImage = new ByteArrayOutputStream();

            out = new FileOutputStream(bitmapFile);
            out.write(bytesOfImage.toByteArray());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesOfImage);
            // out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmapFile.getAbsolutePath();
    }

}
