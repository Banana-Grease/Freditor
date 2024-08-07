package me.oscarcusick.main.DrawElements;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;

/**
 * @deprecated This Class Is Very Simple And To Be Inherited From To Create More Advanced Text Fields
 */
public class TextElement {

    public Vector2<Integer> OriginPoint;
    public String Text;

    public Font DrawFont;

    public TextElement(Vector2<Integer> OriginPoint, Font DrawFont, String Text) {
        this.OriginPoint = OriginPoint;
        this.Text = Text;
        this.DrawFont = DrawFont;
    }

    /**
     * @param g Graphics Object To Parse
     * @param c The Color To Draw The Text In
     */
    public void Draw(Graphics g, Color c) {
        g.setColor(c);
        g.setFont(DrawFont);
        g.drawString(Text, OriginPoint.GetValue(Vector2.Dimensions.X), OriginPoint.GetValue(Vector2.Dimensions.Y));
    }

}
