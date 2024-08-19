package me.oscarcusick.main.Engine.DataTypes;

import java.util.Objects;

public class Identification {
    String IDNumber = null;

    public void setIDNumber(Identification IDNumber) {
        this.IDNumber = IDNumber.IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String GetIDNumber() {
        return IDNumber;
    }

    public boolean Equals(Identification ID1, Identification ID2) {
        return ID1.IDNumber.equals(ID2.IDNumber);
    }
}
