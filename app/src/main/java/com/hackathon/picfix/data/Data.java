package com.hackathon.picfix.data;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by root on 7/4/15.
 */
public class Data {
    private String[] data = {"Rotate", "Blur", "Brightness", "Shading", "Black Filter",
            "Saturation Filter", "Snow Effect", "Flea Effect", "Tint",
            "flip", "WaterMark", "Resize", "Crop", "sketch", "Hue"};

    public String[] getData() {
        return data;
    }
}
