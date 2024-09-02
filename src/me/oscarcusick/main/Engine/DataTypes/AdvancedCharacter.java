package me.oscarcusick.main.Engine.DataTypes;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;

public class AdvancedCharacter {

    private static final Font DefaultDisplayFont = new Font("Cascadia Code Regular", Font.BOLD, 20);

    private Vector2<Integer> CharacterDimensions;

    private char Character;
    private Font DisplayFont;

    private Graphics2D G;

    // updates the dimensions of the character for once it will be displayed
    // this should be called after anything is changed so the information is always accurate when drawing from arrays etc
    private void UpdateDimensions() {
        CharacterDimensions = new Vector2<Integer>(this.G.getFontMetrics(DisplayFont).charWidth(Character), this.G.getFontMetrics(DisplayFont).getHeight());
    }

    // constructor
    public AdvancedCharacter(Graphics2D G, char DefaultCharacter) {
        this.G = G;

        Character = DefaultCharacter; // Set Character
        DisplayFont = DefaultDisplayFont;

        // update character width and height
        UpdateDimensions();
    }

    // draw the character
    public void Draw(Vector2<Integer> OriginPoint) {
        G.setFont(DisplayFont);
        G.drawString(String.valueOf(Character), OriginPoint.GetValue(Vector2.Dimensions.X), OriginPoint.GetValue(Vector2.Dimensions.Y));
    }

    public char GetCharacter() {
        return this.Character;
    }
    public void SetCharacter(char NewCharacter) {
        this.Character = NewCharacter;
        UpdateDimensions(); // update dimensions after we update the character
    }

    public Font GetFont() {
        return this.DisplayFont;
    }
    public void SetFont(Font NewFont) {
        this.DisplayFont = NewFont;
        UpdateDimensions();
    }

    public Vector2<Integer> GetDimensions() {
        return this.CharacterDimensions;
    }
}
