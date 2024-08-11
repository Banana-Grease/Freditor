package me.oscarcusick.main;

import me.oscarcusick.main.DrawElements.VisualOnlyElements.TextBox;
import me.oscarcusick.main.DrawElements.VisualOnlyElements.TextElement;
import me.oscarcusick.main.Engine.Timing;
import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class Canvas extends JComponent {

    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing



    // background hue for GayGB
    float[] HSVHue = {0 , 30};



    // Colour Calculation Class / instantiate ColourHelper
    public ColourHelper CH = new ColourHelper();
    // Debug Handler Class
    DebugHandler DH = new DebugHandler();
    // User Interaction Handler
    InteractionHandler IH;
    Timing Time;

    public Canvas(int WindowSizeX, int WindowSizeY, InteractionHandler IH) {
        ScreenDimensions[ScreenX] = WindowSizeX;
        ScreenDimensions[ScreenY] = WindowSizeY;

        this.IH = IH;
        this.Time = new Timing(60);
    }

    public void paint(Graphics g) { // main paint loop
        Time.Update(true);

        // change background color by incrementing HSV Colour Hues
        HSVHue[0] += (float) (85 * Time.GetDeltaTime(Timing.TimeUnits.S));
        HSVHue[1] += (float) (85 * Time.GetDeltaTime(Timing.TimeUnits.S));
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

        TextElement FPSCounter = new TextElement(g, new Vector2<>(30, 30), new Font("Cascadia Code Regular", Font.BOLD, 20), "FPS: " + Time.GetRealFPS(), Color.white);
        FPSCounter.Draw();

        TextBox TB = new TextBox(g, new Vector2<>(50, 50), new Vector2<>(300, 300));
        TB.SetTextContent(new String("There is no feasable way a bee should be able to fly anmd asdf lol asfgw"));
        g2.setColor(Color.white);
        TB.SetDrawOutSideDimensions(true);
        TB.Draw();

        //System.out.println(g2.getFontMetrics().getHeight());

        // done with this draw cycle
        repaint(); // re-draw
    }


}