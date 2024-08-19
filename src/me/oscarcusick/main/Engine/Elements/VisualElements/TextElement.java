package me.oscarcusick.main.Engine.Elements.VisualElements;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;

/**
 * @deprecated This Class Is Very Simple And To Be Inherited From To Create More Advanced Text Fields
 */
public class TextElement {

    Graphics G;
    public Vector2<Integer> OriginPoint;

    public String Text;
    public Font DrawFont;
    public Color DrawColor;

    public TextElement(Graphics G, Vector2<Integer> OriginPoint, Font DrawFont, String Text, Color DrawColor) {
        this.OriginPoint = OriginPoint;
        this.Text = Text;
        this.DrawFont = DrawFont;
        this.DrawColor = DrawColor;
        this.G = G;
    }

    /**
     * @param g Graphics Object To Parse
     * @param c The Color To Draw The Text In
     */
    public void Draw() {
        G.setColor(Color.white);
        G.setFont(DrawFont);
        G.drawString(Text, OriginPoint.GetValue(Vector2.Dimensions.X), OriginPoint.GetValue(Vector2.Dimensions.Y));
    }
}
