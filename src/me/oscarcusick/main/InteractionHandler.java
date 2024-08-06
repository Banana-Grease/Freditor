package me.oscarcusick.main;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class InteractionHandler {

    public static int StoredEventsSize = 3;

    public static int PressedEvent = 0, TypedEvent = 1;

    public KeyEvent[][] PreviousEvents = new KeyEvent[2][StoredEventsSize];

    public boolean[] HasUpdated = {false, false};

    public InteractionHandler() {
        // innit all elements of array to null
        Arrays.fill(PreviousEvents[0], null);
        Arrays.fill(PreviousEvents[1], null);
    }

    void RegisterNewEvent(KeyEvent Event, boolean IsPressedNotTyped) {
        // pushback all current elements into the next index to make room for a new one
        for (int i = 0; i < StoredEventsSize - 1; i++) {
            if (IsPressedNotTyped)
                PreviousEvents[PressedEvent][i + 1] = PreviousEvents[PressedEvent][i];
            else
                PreviousEvents[TypedEvent][i + 1] = PreviousEvents[TypedEvent][i];
        }
        if (IsPressedNotTyped) {
            PreviousEvents[PressedEvent][0] = Event;
            HasUpdated[PressedEvent] = true;
        } else {
            PreviousEvents[TypedEvent][0] = Event;
            HasUpdated[PressedEvent] = true;
        }
    }
}
