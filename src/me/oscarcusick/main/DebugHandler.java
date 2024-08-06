package me.oscarcusick.main;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DebugHandler {
    // program info ported through the update func

    // screen size
    static final int ScreenX = 0, ScreenY = 1; // definition of element iterator thing
    int[] ScreenDimensions = new int[2]; // to store size of window when initializing

    // all the info will go through this, a name and a value tp display
    HashMap<String, Double> DebugData = new HashMap<>();

    public void UpdateDBGInfo(HashMap<String, Double> DebugData, int ScreenSizeX, int ScreenSizeY) {
        this.DebugData = DebugData;

        ScreenDimensions[ScreenX] = ScreenSizeX;
        ScreenDimensions[ScreenY] = ScreenSizeY;
    }

    public void DrawDebug(Graphics2D g2, GradientPaint BackGroundColour) {
        // vars for positioning the menu and related text
        int MenuX = (int) (ScreenDimensions[ScreenX] * .1), MenuY = (int) (ScreenDimensions[ScreenY] * .1), MenuWidth = (int) (ScreenDimensions[ScreenX] * .6), MenuHeight = (int) (ScreenDimensions[ScreenY] * .6);

        //background menu
        g2.setPaint(BackGroundColour);
        g2.fillRoundRect((int) (ScreenDimensions[ScreenX] * .1)-4, (int) (ScreenDimensions[ScreenY] * .1)-4, (int) (ScreenDimensions[ScreenX] * .6) + 8, (int) (ScreenDimensions[ScreenY] * .6) + 8, 50, 50);
        g2.setColor(new Color(0, 0, 0, 190));
        g2.fillRoundRect(MenuX, MenuY, MenuWidth, MenuHeight, 50, 50);

        // Draw Debug Menu Title
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2.setColor(Color.white);
        g2.drawString("Debug Data", (int)(MenuWidth/2), (int) (MenuY + (MenuY * .5)));

        // Draw Debug Data
        Set<String> KS = DebugData.keySet();
        Iterator<String> KSIterator = KS.iterator();
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        for (int i = 0; i < DebugData.keySet().size(); i++) {
            if (!KS.iterator().hasNext()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String Text = KSIterator.next();
            Text += DebugData.get(Text);
            g2.drawString(Text, (int)(MenuX + (MenuX * .3)), (int)((MenuY*2) + i * 25));
        }

    }

}