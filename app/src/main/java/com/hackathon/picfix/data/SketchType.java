package com.hackathon.picfix.data;

/**
 * Sketch types which ranges from 3-5.
 *
 * @author Shilu
 * @date 7/4/15
 */
public enum SketchType {

    NEGATIVE(3),
    PENCIL_SKETCH(4),
    COLOR_PENCIL_SKETCH(5);


    private final int type;

    SketchType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
