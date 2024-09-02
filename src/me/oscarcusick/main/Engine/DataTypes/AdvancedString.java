package me.oscarcusick.main.Engine.DataTypes;

import java.util.ArrayList;

public class AdvancedString {

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

    public int IndexOf(String QueryString, int StartIndex) {
        for (int i = StartIndex; i < this.GetRegularStringData().length(); i++) {
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

    // ---

    public void AddCharacter(AdvancedCharacter NewCharacter) {
        this.AdvancedStringData.add(NewCharacter);
    }
    public void RemoveCharacter(int CharacterIndex) {
        this.AdvancedStringData.remove(CharacterIndex);
    }
}
