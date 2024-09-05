package me.oscarcusick.main;

import me.oscarcusick.main.Engine.DataTypes.AdvancedCharacter;
import me.oscarcusick.main.Engine.DataTypes.AdvancedString;
import me.oscarcusick.main.Engine.ElementRegistry;
import me.oscarcusick.main.Engine.Elements.VisualElements.AdvancedTextBox;
import me.oscarcusick.main.Engine.Elements.VisualElements.TextBox;
import me.oscarcusick.main.Engine.Elements.InteractiveElements.Button;
import me.oscarcusick.main.Engine.Timing;
import me.oscarcusick.main.Engine.UserInput.InteractionHandler;
import me.oscarcusick.main.Engine.Utility.ColourHelper;
import me.oscarcusick.main.Engine.Math.Vector2;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class Canvas extends JComponent {

    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing


    ElementRegistry ER = new ElementRegistry();


    // background hue for GayGB
    float[] HSVHue = {0 , 30};


    // Colour Calculation Class / instantiate ColourHelper
    public ColourHelper CH = new ColourHelper();
    // Debug Handler Class
    DebugHandler DH = new DebugHandler();
    // User Interaction Handler
    InteractionHandler IH;
    Timing Time;

    AdvancedTextBox ATB = new AdvancedTextBox(new Vector2<Integer>(200, 200), new Vector2<Integer>(200, 200));

    public Canvas(int WindowSizeX, int WindowSizeY, InteractionHandler IH, ElementRegistry ER) {
        ScreenDimensions[ScreenX] = WindowSizeX;
        ScreenDimensions[ScreenY] = WindowSizeY;

        this.IH = IH;
        this.Time = new Timing(60);
        this.ER = ER;

        ER.RegisterNewElement(ElementRegistry.ElementTypes.Button, new Button(new Vector2<Integer>(70, 70), new Vector2<Integer>(100, 100), "Bold"));

    }

    public void paint(Graphics g) { // main paint loop
        // Graphics Interface Setup
        Graphics2D g2 = (Graphics2D) g; // extend Graphics to Graphics2D to enable more methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Time.Update();
        ER.ElementCheck(IH);

        // change background color by incrementing HSV Colour Hues
        HSVHue[0] += (float) (85 * Time.GetDeltaTime(Timing.TimeUnits.S));
        HSVHue[1] += (float) (85 * Time.GetDeltaTime(Timing.TimeUnits.S));
        if (HSVHue[0] >= 360) {
            HSVHue[0] = 0;
        } if (HSVHue[1] >= 360) {
            HSVHue[1] = 0;
        }

        // Start Render Cycle
        // draw GayGB background
        GradientPaint BackGroundPaint = new GradientPaint(new Point2D.Float((int)(ScreenDimensions[ScreenX] / 3), (int)(ScreenDimensions[ScreenY] / 3)), CH.transformHSVtoRGB((int)HSVHue[0], 1, 1), new Point2D.Float((int)((ScreenDimensions[ScreenX] / 3) * 2), (int)((ScreenDimensions[ScreenY] / 3) * 2)), CH.transformHSVtoRGB((int)HSVHue[1], 1, 1));
        g2.setPaint(BackGroundPaint);
        g2.fillRect(0,0, ScreenDimensions[ScreenX], ScreenDimensions[ScreenY]);


        g2.setColor(Color.white);
        TextBox FPSCounter = new TextBox(g2, new Vector2<>(10, 405), new Vector2<>(150, 25));
        FPSCounter.SetTextWrap(false);
        FPSCounter.SetTextContent("FPS: " + (int)Time.GetRealFPS());
        FPSCounter.SetFont(new Font("Cascadia Code Regular", Font.BOLD, 15));
        FPSCounter.SetDrawCenteringType(TextBox.CenteringTypes.Center);
        FPSCounter.SetDrawBoundingBox(true);
        FPSCounter.Draw();

        ER.ElementDraw(g2);

        g2.setColor(Color.red);
        g2.fillRect(152, 152, 2, 2);
        g2.fillRect(82, 82, 2, 2);

        //System.out.println(g2.getFontMetrics().getHeight());
        // done with this draw cycle
        //repaint(); // re-draw

        AdvancedString TempStr = new AdvancedString(new ArrayList<>());
        TempStr = TempStr.FromRegularStringData("I like men", new Font("Cascadia Code Regular", Font.BOLD, 20), null);

        ATB.AddNewLine(TempStr);

        //ATB.AddCharacterToLine(0, new AdvancedCharacter(g2, 'a'));
        //ATB.AddCharacterToLine(0, new AdvancedCharacter(g2, 'b'));
        //ATB.AddCharacterToLine(0, new AdvancedCharacter(g2, 'c'));
        //ATB.AddCharacterToLine(0, new AdvancedCharacter(g2, 'd'));
        //ATB.AddCharacterToLine(0, new AdvancedCharacter(g2, 'e'));

        ATB.Draw(g2);
    }
    
}