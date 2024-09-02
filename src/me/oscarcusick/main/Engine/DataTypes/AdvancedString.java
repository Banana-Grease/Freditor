package me.oscarcusick.main.Engine.DataTypes;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class AdvancedString extends ArrayList<AdvancedString> {

    private ArrayList<AdvancedCharacter> AdvancedStringData;

    public AdvancedString(ArrayList<AdvancedCharacter> NewString) {
        this.AdvancedStringData = NewString;
    }

    public String GetRegularStringData() {
        String ReturnString = "";
        for (AdvancedCharacter AdvC : AdvancedStringData)
            ReturnString += AdvC.GetCharacter();
        return ReturnString;
    }

    public ArrayList<AdvancedCharacter> GetRawData() {
        return this.AdvancedStringData;
    }

    public AdvancedString SubString(int StartIndex, int EndIndex) {
        return new AdvancedString((ArrayList<AdvancedCharacter>) AdvancedStringData.subList(StartIndex, EndIndex));
    }

    // works to find the first appearance from any point of any string
    public int IndexOf(String QueryString, int StartIndex) {
        for (int i = StartIndex; i < this.GetRegularStringData().length(); i++) {
            // make sure the query isn't longer than the amount of chars we have left, if so return -1
            if (QueryString.length() > this.GetRegularStringData().length() - i) {
                return -1;
            }

            // if we have a match
            if (QueryString.equals(this.GetRegularStringData().substring(i, QueryString.length() + i))) {
                return i;
            }
        }
        return -1;
    }
    public int IndexOf(String QueryString) {
        return IndexOf(QueryString, 0);
    }

    // return the tallest character in the string
    // if there are multiple of the same height, the first one found will be returned
    public int GetTallestCharacter() {
        int TallestCharacterIndex = 0, TallestCharacterHeight = 0;

        for (int i = 0; i < this.AdvancedStringData.size(); i++) {
            if (TallestCharacterHeight < this.AdvancedStringData.get(i).GetDimensions().GetValue(Vector2.Dimensions.Y)) {
                TallestCharacterHeight = this.AdvancedStringData.get(i).GetDimensions().GetValue(Vector2.Dimensions.Y);
                TallestCharacterIndex = i;
            }
        }

        return TallestCharacterIndex;
    }
    // get the height of the tallest character, or first one found
    public int GetTallestCharacterHeight(Graphics2D G) {
        return G.getFontMetrics(this.AdvancedStringData.get(GetTallestCharacter()).GetFont()).getHeight();
    }

    // ---

    public void AddCharacter(AdvancedCharacter NewCharacter) {
        this.AdvancedStringData.add(NewCharacter);
    }
    public void RemoveCharacter(int CharacterIndex) {
        this.AdvancedStringData.remove(CharacterIndex);
    }
    public void SetCharacter(int CharacterIndex, AdvancedCharacter NewCharacter) {
        this.AdvancedStringData.set(CharacterIndex, NewCharacter);
    }
}
