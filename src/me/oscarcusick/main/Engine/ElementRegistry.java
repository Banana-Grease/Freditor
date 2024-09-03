package me.oscarcusick.main.Engine;

import me.oscarcusick.main.Engine.Elements.InteractiveElements.Button;
import me.oscarcusick.main.Engine.UserInput.InteractionHandler;
import me.oscarcusick.main.Engine.Utility.GeneralUtility;
import me.oscarcusick.main.Engine.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

// this is a class that should be used to 'register' interactive elements
// such as buttons. these will be stored in a list then this class will handle the user input or whatever
public class ElementRegistry {

    GeneralUtility GU = new GeneralUtility();

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
    public void ElementCheck(InteractionHandler GlobalInteractionHandler) {
        for (Button B : ButtonList) {
            // if there has been a NEW mouse event, search all the buttons
            if (GlobalInteractionHandler.MouseEventsHaveUpdated) {
                // if there is a mouse event where the mouse has clicked within a button's area, reset the mouse event flag and update button state
                if (GU.IsWithinArea(new Vector2<Integer>((int) GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().getX(), (int) GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().getY()), B.GetAdjustedOrigin(), B.GetAdjustedDimensions(), true)) {
                    B.SetPressedState(!B.GetPressedState());
                    GlobalInteractionHandler.MouseEventsHaveUpdated = false; // set back too false to avoid registering the same click twice
                }
            }
        }
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
