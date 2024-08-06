package me.oscarcusick.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.time.Clock;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.Delayed;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Canvas extends JComponent {

    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing

    // delta time shit
    // DeltaTime is the difference in time between frames, measured in milliseconds compared to Unity's seconds.
    // To make graphics or anything change at a static rate, independent of FPS, multiply the change by DeltaTime, it will move quicker but just change it by less
    Long CurrentTime, LastTime, DeltaTime;
    static int TargetFPS = 60; // how many times will the screen update per second
    float RealFPS = 0; // what is the real FPS count for this loop, updated per render call
    float MSPerFrame = (1000f / TargetFPS);


    // background hue for GayGB
    float[] HSVHue = {0 , 30};

    // Colour Calculation Class / instantiate ColourHelper
    public ColourHelper CH = new ColourHelper();
    // Debug Handler Class
    DebugHandler DH = new DebugHandler();
    // User Interaction Handler
    InteractionHandler IH;

    public Canvas(int WindowSizeX, int WindowSizeY, InteractionHandler IH) {
        ScreenDimensions[ScreenX] = WindowSizeX;
        ScreenDimensions[ScreenY] = WindowSizeY;

        this.IH = IH;

        // delta time shit
        CurrentTime = System.nanoTime();
    }

    public void paint(Graphics g) { // main paint loop
        // DeltaTime
        LastTime = CurrentTime;
        CurrentTime = System.nanoTime();
        DeltaTime = (CurrentTime - LastTime) / 1000000; // milliseconds
        RealFPS = 1000f / DeltaTime; // what is the actual calculated FPS for this frame

        // update window information to properly size the render scale & prevent making the window too small
        //ScreenDimensions[ScreenX] = javax.swing.FocusManager.getCurrentManager().getActiveWindow().getWidth(); // causes errors when tabbed out
        //ScreenDimensions[ScreenY] = javax.swing.FocusManager.getCurrentManager().getActiveWindow().getHeight();

        if (MSPerFrame > DeltaTime) { // sleep if we are running too fast
            try {
                Thread.sleep((int)(MSPerFrame - DeltaTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // change background color by incrementing HSV Colour Hues
        HSVHue[0] += (.1f * DeltaTime);
        HSVHue[1] += (.1f * DeltaTime);
        if (HSVHue[0] >= 360) {
            HSVHue[0] = 0;
        } if (HSVHue[1] >= 360) {
            HSVHue[1] = 0;
        }

        // Graphics Interface Setup
        Graphics2D g2 = (Graphics2D) g; // extend Graphics to Graphics2D to enable more methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Start Render Cycle

        // draw GayGB background
        GradientPaint BackGroundPaint = new GradientPaint(new Point2D.Float((int)(ScreenDimensions[ScreenX] / 3), (int)(ScreenDimensions[ScreenY] / 3)), CH.transformHSVtoRGB((int)HSVHue[0], 1, 1), new Point2D.Float((int)((ScreenDimensions[ScreenX] / 3) * 2), (int)((ScreenDimensions[ScreenY] / 3) * 2)), CH.transformHSVtoRGB((int)HSVHue[1], 1, 1));
        g2.setPaint(BackGroundPaint);
        g2.fillRect(0,0, ScreenDimensions[ScreenX], ScreenDimensions[ScreenY]);


        g2.setColor(Color.white);
        g2.drawString("FPS: " + RealFPS, 10, 10);


        // done with this paint
        repaint(); // repaint
    }


}