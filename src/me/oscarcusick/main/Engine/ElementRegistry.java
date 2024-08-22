package me.oscarcusick.main.Engine;

import me.oscarcusick.main.Engine.Elements.InteractiveElements.Button;

import java.awt.*;
import java.util.ArrayList;

// this is a class that should be used to 'register' interactive elements
// such as buttons. these will be stored in a list then this class will handle the user input or whatever
public class ElementRegistry {

    public enum ElementTypes {
        Button
    }

    private ArrayList<Button> ButtonList;

    public ElementRegistry() {
        this.ButtonList = new ArrayList<>();
    }

    public void ElementDraw(Graphics2D G) {
        for (Button B : ButtonList) {
            B.Draw(G);
        }
    }

    /**
     * used to handle inputs for all the registered buttons
     * must be called once per program loop
     */
    public void ElementCheck() {

    }

    public <T> void RegisterNewElement(ElementTypes ElementType, T Element) {
        switch (ElementType.ordinal()) {
            case 0: // Button
                ButtonList.add((Button) Element);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

}
