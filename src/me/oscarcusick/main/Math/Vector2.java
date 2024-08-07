package me.oscarcusick.main.Math;

import java.util.ArrayList;

public class Vector2 <T> {

    // This enum does not need values attached as we use Dimensions.ordinal() to get the enums position in the list (x = 0), (y = 1)

    public enum Dimensions {
        X,
        Y
    }

    private ArrayList<T> Dimensions = new ArrayList<>();

    /**
     *
     * @param DimensionX A Value To Be Stored For The X-Axis
     * @param DimensionY A Value To Be Stored For The Y-Axis
     */
    // Constructor
    public Vector2(T DimensionX, T DimensionY) {
        Dimensions.add(DimensionX);
        Dimensions.add(DimensionY);
    }

    /**
     * @param Dimension Use The 'Dimensions' Enumerator To Access Either 'X' or 'Y' Dimensions
     * @param Value The Value To Set On This Dimension
     */
    public void SetValue(Dimensions Dimension, T Value) {
        Dimensions.set(Dimension.ordinal(), Value);
    }

    /**
     * @param Dimension Which Dimension To Get The Value From
     * @return Returns The Value Stored At The Specified Dimension
     */
    public T GetValue(Dimensions Dimension) {
        return Dimensions.get(Dimension.ordinal());
    }

}
