package me.oscarcusick.main.Engine.Elements.VisualElements;

import me.oscarcusick.main.Engine.DataTypes.AdvancedCharacter;
import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class AdvancedTextBox {

    private Graphics2D G;

    // this is an array list of array lists. why I am doing this is because the first list is sentence height and the second is the characters for that line
    private ArrayList<ArrayList<AdvancedCharacter>> CharacterLines = new ArrayList<>();

    Vector2<Integer> OriginPoint, Dimensions;

    // weather to draw the 'literal bounding box' lol
    private boolean DrawBoundingBox = false;
    private Color BoundingBoxColour = new Color(0, 0, 0, 127);


    public AdvancedTextBox(Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions) {
        this.OriginPoint = OriginPoint;
        this.Dimensions = Dimensions;
    }

    public void Draw() {

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

    // -------------- Character and line Manipulation Below

    public ArrayList<ArrayList<AdvancedCharacter>> GetCharacterLines() {
        return this.CharacterLines;
    }
    public int GetAmountOfLines() {
        return this.CharacterLines.size();
    }

    public void AddNewLine(ArrayList<AdvancedCharacter> NewLine) {
        this.CharacterLines.add(NewLine);
    }
    public void RemoveLine(int Index) {
        this.CharacterLines.remove(Index);
    }

    public void AddCharacterToLine(int TargetLineIndex, AdvancedCharacter NewCharacter) {
        this.CharacterLines.get(TargetLineIndex).add(NewCharacter);
    }
    public void RemoveCharacterFromLine(int TargetLineIndex, int TargetCharacterIndex) {
        this.CharacterLines.get(TargetLineIndex).remove(TargetCharacterIndex);
    }
    public void SetCharacterOnLine(int TargetLineIndex, int TargetCharacterIndex, AdvancedCharacter NewCharacter) {
        this.CharacterLines.get(TargetLineIndex).set(TargetCharacterIndex, NewCharacter);
    }
}
