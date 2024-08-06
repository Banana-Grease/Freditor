package me.oscarcusick.main;/*
 *
 * ALL CREDITS FOR THIS CLASS TO CONVERT HSV TO RGB GO TO;
 * http://www.java2s.com/example/java/2d-graphics/convert-a-hsv-color-value-to-rgb-colorspace.html
 *
 * */

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColourHelper {
    /** first 60 degrees hue are red to yellow in HSV. */
    private static final int RED_YELLOW = 1;
    /** second 60 degrees hue are yellow to green in HSV. */
    private static final int YELLOW_GREEN = 2;
    /** third 60 degrees hue are green to cyan in HSV. */
    private static final int GREEN_CYAN = 3;
    /** fourth 60 degrees hue are cyan to blue in HSV. */
    private static final int CYAN_BLUE = 4;
    /** fifth 60 degrees hue are blue to magenta in HSV. */
    private static final int BLUE_MAGENTA = 5;
    /** last 60 degrees hue are magenta to red in HSV. */
    private static final int MAGENTA_RED = 6;
    /**
     * Splits the 360 degree hue into 6 parts.
     */
    private static final float HUE_SPLIT = 60.f;
    /**
     * Maximum amount of the hue value.
     */
    private static final int HUE_MAX = 360;

    public static Color transformHSVtoRGB(final int hue, final float sat,
                                          final float val) {
        if (hue < 0 || hue > HUE_MAX) {
            throw new IllegalArgumentException("hue needs to be between"
                    + " 0 and 360");
        }

        if (sat < 0.0 || sat > 1.0) {
            throw new IllegalArgumentException(
                    "saturation needs to be between" + " 0.0 and 1.0");
        }

        if (val < 0.0 || val > 1.0) {
            throw new IllegalArgumentException("value needs to be between"
                    + " 0.0 and 1.0");
        }

        /* check wikipedia or something similar for the actual algorithm,
           it's standard. */
        int h = (int) (hue / HUE_SPLIT);
        float f = (hue / HUE_SPLIT) - h;
        float p = val * (1 - sat);
        float q = val * (1 - sat * f);
        float t = val * (1 - sat * (1 - f));

        switch (h) {
            case RED_YELLOW:
                return new Color(q, val, p);
            case YELLOW_GREEN:
                return new Color(p, val, t);
            case GREEN_CYAN:
                return new Color(p, q, val);
            case CYAN_BLUE:
                return new Color(t, p, val);
            case BLUE_MAGENTA:
                return new Color(val, p, q);
            case MAGENTA_RED:
            default: //<- default value to ensure 360 != 0?
                return new Color(val, t, p);
        }
    }
}