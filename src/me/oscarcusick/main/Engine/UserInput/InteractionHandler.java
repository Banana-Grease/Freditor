package me.oscarcusick.main.Engine.UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * it is recommended not to mess with this class instance unless you are writing code for the engine.
 * this is because there are no safety features written into this class to prevent un-intentional behaviour
 * everything in this class is public access
 */
public class InteractionHandler {

    public static int StoredEventsSize = 3;

    public static int KeyPressedEvent = 0, KeyTypedEvent = 1;
    public static int MouseClickedEvent = 0, MousePressedEvent = 1, MouseReleasedEvent = 2;

    public KeyEvent[][] PreviousKeyEvents = new KeyEvent[2][StoredEventsSize];

    public MouseEvent[] PreviousMouseEvents = new MouseEvent[StoredEventsSize];

    public boolean[] KeyEventsHaveUpdated = {false, false};
    public boolean MouseEventsHaveUpdated = false;

    public InteractionHandler() {
        // innit all elements of arrays to null
        Arrays.fill(PreviousKeyEvents[0], null);
        Arrays.fill(PreviousKeyEvents[1], null);

        Arrays.fill(PreviousMouseEvents, null);
    }

    public void RegisterNewKeyEvent(KeyEvent Event, boolean IsPressedNotTyped) {
        // pushback all current elements into the next index to make room for a new one
        for (int i = 0; i < StoredEventsSize - 1; i++) {
            if (IsPressedNotTyped)
                PreviousKeyEvents[KeyPressedEvent][i + 1] = PreviousKeyEvents[KeyPressedEvent][i];
            else
                PreviousKeyEvents[KeyTypedEvent][i + 1] = PreviousKeyEvents[KeyTypedEvent][i];
        }
        if (IsPressedNotTyped) {
            PreviousKeyEvents[KeyPressedEvent][0] = Event;
            KeyEventsHaveUpdated[KeyPressedEvent] = true;
        } else {
            PreviousKeyEvents[KeyTypedEvent][0] = Event;
            KeyEventsHaveUpdated[KeyPressedEvent] = true;
        }
    }

    public void RegisterNewMouseEvent(MouseEvent Event) {
        // pushback all current elements into the next index to make room for a new one
        for (int i = 0; i < StoredEventsSize - 1; i++) {
            PreviousMouseEvents[i + 1] = PreviousMouseEvents[i];
        }
        PreviousMouseEvents[0] = Event;
        MouseEventsHaveUpdated = true;
    }
}
