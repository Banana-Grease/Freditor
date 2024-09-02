package me.oscarcusick.main.Engine.Elements.VisualElements;

import me.oscarcusick.main.Engine.DataTypes.AdvancedCharacter;
import me.oscarcusick.main.Engine.DataTypes.AdvancedString;
import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class AdvancedTextBox {

    private Graphics2D G;

    // this is an array list of array lists. why I am doing this is because the first list is sentence height and the second is the characters for that line
    private ArrayList<AdvancedString> AdvancedStringsData = new ArrayList<>();

    Vector2<Integer> OriginPoint, Dimensions;

    // weather to draw the 'literal bounding box' lol
    private boolean DrawBoundingBox = false;
    private Color BoundingBoxColour = new Color(0, 0, 0, 127);

    // weather to indent an entire line due to the above line or just the characters that need to be pushed down
    // right now this don't do nuthin' that's coming later due to overlap concerns
    private boolean IndentEntireLine = true;

    public AdvancedTextBox(Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions) {
        this.OriginPoint = OriginPoint;
        this.Dimensions = Dimensions;
    }

    // how low to draw this specific character so no overlap occurs
    private int GetDrawY(int TargetLine) {
        int ReturnDrawY = 0;

        if (IndentEntireLine && TargetLine <= AdvancedStringsData.size()) {
            for (int i = 0; i < TargetLine; i++) {
                ReturnDrawY += AdvancedStringsData.get(i).GetTallestCharacterHeight(G);
            }
        }

        return ReturnDrawY;
    }
    // how far along to draw a certain character
    private int GetDrawX(int TargetLine, int TargetCharacter) {
        int ReturnDrawX = 0;

        for (int i = 1; i < AdvancedStringsData.get(TargetLine).GetRawData().size() + 1; i++) {

            if ( i > TargetCharacter)
                return ReturnDrawX;

            ReturnDrawX += AdvancedStringsData.get(TargetLine).GetRawData().get(i - 1).GetDimensions().GetValue(Vector2.Dimensions.X);
        }

        return ReturnDrawX;
    }

    public void Draw() {

        // loop all characters on each line and calculate their X & Y Position
        for (int i = 0; i < AdvancedStringsData.size(); i++) {
            for (int y = 0; y < AdvancedStringsData.get(i).GetRawData().size(); y++) {
                AdvancedStringsData.get(i).GetRawData().get(y).SetOriginPoint(new Vector2<Integer>(GetDrawX(i, y) + this.OriginPoint.GetValue(Vector2.Dimensions.X), GetDrawY(i) + this.OriginPoint.GetValue(Vector2.Dimensions.Y)));
            }
        }

        G.setColor(Color.white);

    for (AdvancedString AdvString : AdvancedStringsData) {
        for (AdvancedCharacter AdvCharacter : AdvString.GetRawData()) {
            AdvCharacter.Draw();
            System.out.println("Drew '" + AdvCharacter.GetCharacter() + "' @Point: (" + AdvCharacter.GetOriginPoint().GetValue(Vector2.Dimensions.X) + ", " + AdvCharacter.GetOriginPoint().GetValue(Vector2.Dimensions.Y) + ")");
        }
    }

    }

    public boolean GetDrawBoundingBox() {
        return this.DrawBoundingBox;
    }
    public void SetDrawBoundingBox(boolean DrawBoundingBox) {
        this.DrawBoundingBox = DrawBoundingBox;
    }

    public Color GetBoundingBoxColour() {
        return this.BoundingBoxColour;
    }
    public void SetBoundingBoxColour(Color BoundingBoxColour) {
        this.BoundingBoxColour = BoundingBoxColour;
    }

    public void SetGraphics2D(Graphics2D NewGraphics2D) {
        this.G = NewGraphics2D;
    }

    // -------------- Character and line Manipulation Below

    public ArrayList<AdvancedString> GetCharacterLines() {
        return this.AdvancedStringsData;
    }
    public int GetAmountOfLines() {
        return this.AdvancedStringsData.size();
    }

    public void AddNewLine(AdvancedString NewLine) {
        this.AdvancedStringsData.add(NewLine);
    }
    public void RemoveLine(int Index) {
        this.AdvancedStringsData.remove(Index);
    }

    public void AddCharacterToLine(int TargetLineIndex, AdvancedCharacter NewCharacter) {
        this.AdvancedStringsData.get(TargetLineIndex).AddCharacter(NewCharacter);
    }
    public void RemoveCharacterFromLine(int TargetLineIndex, int TargetCharacterIndex) {
        this.AdvancedStringsData.get(TargetLineIndex).RemoveCharacter(TargetCharacterIndex);
    }
    public void SetCharacterOnLine(int TargetLineIndex, int TargetCharacterIndex, AdvancedCharacter NewCharacter) {
        this.AdvancedStringsData.get(TargetLineIndex).SetCharacter(TargetCharacterIndex, NewCharacter);
    }
}
