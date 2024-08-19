package me.oscarcusick.main.Engine.Elements.InteractiveElements;

import me.oscarcusick.main.Engine.DataTypes.Identification;
import me.oscarcusick.main.Engine.Utility.GeneralUtility;
import me.oscarcusick.main.Math.Vector2;

import java.security.NoSuchAlgorithmException;

public class Button {
    private Vector2<Integer> OriginPoint, Dimensions;

    String ButtonName;

    Identification ButtonID;

    public Button(Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions, String ButtonName) {
        GeneralUtility GU = new GeneralUtility();

        this.OriginPoint = OriginPoint;
        this.Dimensions = Dimensions;

        this.ButtonName = ButtonName;
        // set ButtonID hash
        try {
            this.ButtonID.setIDNumber(GU.Hash256(this.ButtonName));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Identification GetButtonID() {
        return ButtonID;
    }

}
