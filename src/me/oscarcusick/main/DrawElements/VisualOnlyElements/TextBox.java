package me.oscarcusick.main.DrawElements.VisualOnlyElements;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class TextBox extends TextElement {

    Vector2<Integer> Dimensions;
    boolean DoTextWrap = true, DoDrawOutSideDimensions = false;

    public TextBox(Graphics G, Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions) {
        super(G, OriginPoint, new Font("Cascadia Code Regular", Font.BOLD, 20), "", Color.white); // slap in some default values that there will be methods to change
        this.G = G;
        this.Dimensions = Dimensions;
    }

    @Override
    public void Draw() {
        // if text wrapping all the split lines will be seperate strings in here
        ArrayList<String> SplitStrings = new ArrayList<>();

        // see if the text will collide with the border on x-axis
        int TotalWidth = 0;
        {
            ArrayList<Integer> SplitterIndexes = new ArrayList<>();
            SplitterIndexes.add(0);

            for (int i = 1; i < super.Text.toCharArray().length+1; i++) {
                TotalWidth += G.getFontMetrics().charWidth(super.Text.toCharArray()[i-1]);

                System.out.println("current char: " + super.Text.toCharArray()[i-1]);

                if (TotalWidth >= Dimensions.GetValue(Vector2.Dimensions.X)) {

                    System.out.println("Width: " + TotalWidth);

                    SplitterIndexes.add(i-1);

                    SplitStrings.add((String) super.Text.subSequence(SplitterIndexes.get(i-1), SplitterIndexes.get(i)));

                    TotalWidth = 0;
                }
            }

            for (String Str : SplitStrings) {
                System.out.println(Str);
            }
        }




    }

    public void SetTextContent(String NewText) {
        super.Text = NewText;
    }

    public void SetFont(Font NewFont) {
        super.DrawFont = NewFont;
    }

    public void SetDimensions(Vector2<Integer> NewDimensions) {
        Dimensions = NewDimensions;
    }

    public void SetTextWrap(boolean DoTextWrap) {
        this.DoTextWrap = DoTextWrap;
    }

    public void SetDrawOutSideDimensions(boolean DoDrawOutSideDimensions) {
        this.DoDrawOutSideDimensions = DoDrawOutSideDimensions;
    }

}
