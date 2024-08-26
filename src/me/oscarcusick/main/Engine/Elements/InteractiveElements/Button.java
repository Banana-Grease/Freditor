package me.oscarcusick.main.Engine.Elements.InteractiveElements;

import me.oscarcusick.main.Engine.DataTypes.Identification;
import me.oscarcusick.main.Engine.Utility.GeneralUtility;
import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

public class Button {
    // constants
    public enum ColourTypes {
        NotPressed,
        Pressed,
        Border,
        Shadow
    }

    private Vector2<Integer> OriginPoint, Dimensions;

    private String ButtonName;
    private Identification ButtonID = new Identification();
    private boolean IsPressed = false;

    // Draw Setttings
    private float RoundingFactor = .3f;
    private float BorderFactor = .75f;
    private String DrawLabel;
    private Color[] ButtonColours = new Color[4]; // the size of this array should be the size of ColourTypes


    public Button(Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions, String ButtonName) {
        GeneralUtility GU = new GeneralUtility();

        this.OriginPoint = OriginPoint;
        this.Dimensions = Dimensions;

        this.ButtonName = ButtonName;
        // set ButtonID hash
        try {
            this.ButtonID.setIDNumber(GU.Hash256(this.ButtonName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Draw Settings
        this.DrawLabel = ButtonName;

        this.ButtonColours[ColourTypes.NotPressed.ordinal()] = new Color(245, 245, 245);
        this.ButtonColours[ColourTypes.Pressed.ordinal()] = new Color(163, 163, 163);
        this.ButtonColours[ColourTypes.Border.ordinal()] = new Color(44, 44, 44);
        this.ButtonColours[ColourTypes.Shadow.ordinal()] = new Color(19, 19, 19, 229);
    }

    public void Draw(Graphics2D G) {
        int OriginX = 0, OriginY = 0;
        // draw the button here. this will be called through ElementRegistry


        // draw the shadow of the button
        OriginX = (int)((Dimensions.GetValue(Vector2.Dimensions.X) / 2) + OriginPoint.GetValue(Vector2.Dimensions.X));
        OriginY = (int)((Dimensions.GetValue(Vector2.Dimensions.Y) / 2) + OriginPoint.GetValue(Vector2.Dimensions.Y));
        G.setPaint(new RadialGradientPaint(
                OriginX, // x point
                OriginY, // y point
                (int)((Dimensions.GetValue(Vector2.Dimensions.X) + Dimensions.GetValue(Vector2.Dimensions.Y)) / 2), // radius
                new float[]{0.f, 0.55f}, // fractions?
                new Color[]{this.ButtonColours[ColourTypes.Shadow.ordinal()], new Color(0, 0, 0, 0)}));
        G.fillRoundRect(
                OriginPoint.GetValue(Vector2.Dimensions.X), // origin x
                OriginPoint.GetValue(Vector2.Dimensions.Y), // origin y
                Dimensions.GetValue(Vector2.Dimensions.X), // dimensions x
                Dimensions.GetValue(Vector2.Dimensions.Y), // dimensions y
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * RoundingFactor), // rounding x
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * RoundingFactor));// rounding y

        // draw the border
        G.setColor(ButtonColours[ColourTypes.Border.ordinal()]);
        OriginX = (OriginPoint.GetValue(Vector2.Dimensions.X) + (int)(Dimensions.GetValue(Vector2.Dimensions.X) * BorderFactor) / 2) - Dimensions.GetValue(Vector2.Dimensions.X) / 4;
        OriginY = (OriginPoint.GetValue(Vector2.Dimensions.Y) + (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * BorderFactor) / 2) - Dimensions.GetValue(Vector2.Dimensions.Y) / 4;
        G.fillRoundRect(
                OriginX, // origin x
                OriginY, // origin y
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * BorderFactor), // dimensions x
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * BorderFactor), // dimensions y
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * RoundingFactor), // rounding x
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * RoundingFactor)); // rounding y

        // draw the button
        if (IsPressed)
            G.setColor(ButtonColours[ColourTypes.Pressed.ordinal()]);
        else
            G.setColor(ButtonColours[ColourTypes.NotPressed.ordinal()]);
        G.fillRoundRect(
                OriginX + (int)(Dimensions.GetValue(Vector2.Dimensions.X) * BorderFactor) / 16, // origin x
                OriginY + (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * BorderFactor) / 16, // origin y
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * (BorderFactor * (BorderFactor + BorderFactor / 6))), // dimensions x (this isn't perfectly determining the size)
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * (BorderFactor * (BorderFactor + BorderFactor / 6))), // dimensions y (this isn't perfectly determining the size)
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * RoundingFactor), // rounding x
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * RoundingFactor)); // rounding y

        // draw the label



    }

    public Identification GetButtonID() {
        return ButtonID;
    }

    public String GetButtonName() {
        return ButtonName;
    }

    // used to determine if a user-click was within the button's coordinates
    public Vector2<Integer> GetAdjustedOrigin() {
        return new Vector2<Integer>(
                (int)(OriginPoint.GetValue(Vector2.Dimensions.X) + (int)(Dimensions.GetValue(Vector2.Dimensions.X) * BorderFactor) / 2) - Dimensions.GetValue(Vector2.Dimensions.X) / 4,
                (int)(OriginPoint.GetValue(Vector2.Dimensions.Y) + (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * BorderFactor) / 2) - Dimensions.GetValue(Vector2.Dimensions.Y) / 4);
    }

    // used to determine if a user-click was within the button's coordinates
    public Vector2<Integer> GetAdjustedDimensions() {
        return new Vector2<Integer>(
                (int)(Dimensions.GetValue(Vector2.Dimensions.X) * BorderFactor),
                (int)(Dimensions.GetValue(Vector2.Dimensions.Y) * BorderFactor));
    }

    public void SetPressedState(boolean SetPressedTrue) {
        IsPressed = SetPressedTrue;
    }
    public boolean GetPressedState() {
        return IsPressed;
    }


}
