package me.oscarcusick.main;

import me.oscarcusick.main.Engine.DataTypes.Identification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    static final int WindowSizeX = 640, WindowSizeY = 480;
    public static final String ProgramResourceDirectory = null;

    public static void main(String[] args) {
        // find where to open the window to
        int[] OpenPos = {((MouseInfo.getPointerInfo().getLocation().x) - (WindowSizeX / 2)), ((MouseInfo.getPointerInfo().getLocation().y) - (WindowSizeY / 2))};
        // make sure the window is within the bounds of the user's screen
        if (OpenPos[1] < 0) { // Y-Axis
            OpenPos[1] = 0;
        } if (OpenPos[0] < 0) { // X-Axis on the left
            OpenPos[0] = 0;
        }

        // add key listener
        InteractionHandler IH = new InteractionHandler();
        KeyListener KL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { // higher-level, returns a unicode character from keyboard input
                IH.RegisterNewEvent(e, false);
            }

            @Override
            public void keyPressed(KeyEvent e) { // lower-level, returns the key code from any input
                IH.RegisterNewEvent(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        // init canvas
        Canvas Canvas = new Canvas(WindowSizeX, WindowSizeY, IH);

        // start actual window
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(OpenPos[0], OpenPos[1],WindowSizeX, WindowSizeY);
        window.getContentPane().add(Canvas);
        window.setResizable(false); // if resizing doesn't work properly change this to false

        // Title and Icon
        window.setTitle("Test Window");
        //window.setIconImage(Toolkit.getDefaultToolkit().getImage(IconImageDirectory + "Calculator_Icon.png"));
        // set visible
        window.setVisible(true);

        //for (Font F : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
        //    System.out.println(F.getFontName());
        //}

        Identification ID = new Identification();
        ID.setIDNumber("asdas");

    }
}