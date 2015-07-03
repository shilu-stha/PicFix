package com.hackathon.picfix;

/**
 * Created by leapfrog on 7/3/15.
 */
public interface PicFixViewInterface {

    /**
     * Enable rotation for the imageview
     * @param rotationDegree - Degree to rotate image, range 0-360
     * */
    void setRotationTo(float rotationDegree);


    /**
     * Enable blur of the imageview
     * @param radius - Radius to blur the image view
     * */
    void setBlur(float radius);

    /**
     * Set brightness to the imageview
     * @param brightnessValue - value by how much brightness is set to the imageview
     * */
    void setBrightness(int brightnessValue);

    /**
     * set shading effect to imageview
     * @param shadingColor - integer value of the color by which the shading effect is to be given
     * */
    void setShading(int shadingColor);

    /**
     * set black filter to imageview
     * */
    void applyBlackFilter();

    /**
     * set Hue filter
     * */
    void applyHueFilter(int hueLevel);

    /**
     * set saturation filter
     * @param saturationLevel - integer to set saturation level for imageview
     * */

    void applySaturationFilter(int saturationLevel);


}
