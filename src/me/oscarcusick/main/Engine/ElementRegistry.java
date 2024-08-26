package me.oscarcusick.main.Engine;

import me.oscarcusick.main.Engine.Elements.InteractiveElements.Button;
import me.oscarcusick.main.Engine.UserInput.InteractionHandler;
import me.oscarcusick.main.Math.Vector2;

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
    public void ElementCheck(InteractionHandler GlobalInteractionHandler) {
        for (Button B : ButtonList) {
            // if there has been a NEW mouse event, seach all the buttons
            if (GlobalInteractionHandler.MouseEventsHaveUpdated) {
                // if there is a mouse event where the mouse has clicked within a button's area, reset the mouse event flag and update button state
                if ((GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().x > B.GetAdjustedOrigin().GetValue(Vector2.Dimensions.X) && GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().x < (B.GetAdjustedOrigin().GetValue(Vector2.Dimensions.X) + B.GetAdjustedDimensions().GetValue(Vector2.Dimensions.X))) && (GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().y > B.GetAdjustedOrigin().GetValue(Vector2.Dimensions.Y) && GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().y < (B.GetAdjustedOrigin().GetValue(Vector2.Dimensions.Y) + B.GetAdjustedDimensions().GetValue(Vector2.Dimensions.Y)))) { // condense both if statements into the one
                    B.SetPressedState(!B.GetPressedState());
                    GlobalInteractionHandler.MouseEventsHaveUpdated = false; // set back to false to avoid registering the same click twice
                }

                // debug
                System.out.println("X: " + (GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().x) + ", Y: " + (GlobalInteractionHandler.PreviousMouseEvents[0].getPoint().y));
                System.out.println((B.GetAdjustedOrigin().GetValue(Vector2.Dimensions.Y) + B.GetAdjustedDimensions().GetValue(Vector2.Dimensions.Y)));
                GlobalInteractionHandler.MouseEventsHaveUpdated = false;

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
