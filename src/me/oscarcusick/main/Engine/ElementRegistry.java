package me.oscarcusick.main.Engine;

import me.oscarcusick.main.Engine.Elements.InteractiveElements.Button;

import java.util.ArrayList;

// this is a class that should be used to 'register' interactive elements
// such as buttons. these will be stored in a list then this class will handle the user input or whatever
public class ElementRegistry {

    private ArrayList<Button> ButtonList;

    public ElementRegistry() {
        this.ButtonList = new ArrayList<>();
    }

    /**
     * used to handle inputs for all the registered buttons
     * must be called once per program loop
     */
    public void ElementCheck() {

    }

    public void RegisterNewButton(Button NewButton) {
        this.ButtonList.add(NewButton);
    }

}
