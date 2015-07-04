package com.hackathon.picfix;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;

/**
 * Interface to handle all the image editor function
 */
public interface PicFixViewInterface {

    /**
     * Enable rotation for the bitmap
     *
     * @param rotationDegree - Degree to rotate image, range 0-360
     */
    void setRotationTo(float rotationDegree);


    /**
     * Enable blur of the bitmap
     *
     * @param radius - Radius to blur the image view
     */
    void setBlur(float radius);

    /**
     * Set brightness to the bitmap
     *
     * @param brightnessValue - value by how much brightness is set to the bitmap
     */
    void setBrightness(int brightnessValue);

    /**
     * set shading effect to bitmap
     *
     * @param shadingColor - integer value of the color by which the shading effect is to be given
     */
    void setShading(int shadingColor);

    /**
     * set black filter to bitmap
     */
    void applyBlackFilter();

    /**
     * set saturation filter
     *
     * @param saturationLevel - integer to set saturation level for bitmap
     */

    void applySaturationFilter(int saturationLevel);

    /**
     * set snow effect to bitmap
     */
    void applySnowEffect();


    /**
     * set flea effect to the image
     */
    void applyFleaEffect();


    /**
     * set tint image
     *
     * @param tintDegree - degree by how much tint is to be applied
     */

    void setTintImage(int tintDegree);

//    /**
//     * replace the color of image
//     * @param fromColor - value color from which replace starts
//     *  @param targetColor - color value to which color needs to be replaced
//     * */
//    void replaceColor(int fromColor, int targetColor);

    /**
     * flip the image
     *
     * @param flipType - type of the flip horizontal or vertical
     */

    void flipImage(int flipType);

    /**
     * sets the watermark to the image
     *
     * @param watermark - string to be set as watermark
     * @param location  - {@link Point} where watermark will be displayed
     * @param color     - {@link Color} font color to be set as watermark
     * @param alpha     - alpha to be set to watermark
     * @param size      - size of the watermark text
     * @param underline - set true if underline needed else false
     */
    void setWaterMark(String watermark, Point location, int color, int alpha, int size, boolean underline);

    /**
     * resize the image to given width and height
     *
     * @param width  - integer value passed by user
     * @param height - integer value passed by user
     */
    void resizeImage(int width, int height);

    /**
     * crop the bitmap to from given x and y position up to the given height and width.
     *
     * @param startX - float value passed by user
     * @param startY - float value passed by user
     * @param width  - integer value passed by user
     * @param height - integer value passed by user
     */
    void doCrop(float startX, float startY, int width, int height);

    /**
     * convert bitmap to sketch bitmap
     *
     * @param type      - 3 for negative, 4 for pencil sketch, 5 for color pencil sketch
     * @param threshold - integer value passed by user
     */
    void sketch(int type, int threshold);

    /**
     * set hue filter
     *
     * @param huelevel
     * @return
     */
    ColorFilter applyHue(int huelevel);

    void setGreyScale();

}