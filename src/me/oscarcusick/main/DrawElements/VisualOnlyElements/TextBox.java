package me.oscarcusick.main.DrawElements.VisualOnlyElements;

import me.oscarcusick.main.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TextBox extends TextElement {

    Vector2<Integer> Dimensions;
    boolean DoTextWrap = true, DoDrawOutSideDimensions = true;

    public TextBox(Graphics G, Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions) {
        super(G, OriginPoint, new Font("Cascadia Code Regular", Font.BOLD, 20), "", Color.white); // slap in some default values that there will be methods to change
        this.G = G;
        this.Dimensions = Dimensions;
    }

    @Override
    public void Draw() {
        // if text wrapping all the split lines will be separate strings in here
        ArrayList<String> DrawStrings = new ArrayList<>();


        { // draw the bounds pf tje box first for debug lmfao
            G.drawRect(super.OriginPoint.GetValue(Vector2.Dimensions.X), super.OriginPoint.GetValue(Vector2.Dimensions.Y), Dimensions.GetValue(Vector2.Dimensions.X), Dimensions.GetValue(Vector2.Dimensions.Y));
        }

        // make sure it ends in space for proper splitting
        if (!super.Text.endsWith(" ")) {
            super.Text += " ";
        }

        if (DoTextWrap) {
            int SplitIndex = 0, FromIndex;

            int CurrentTextLineWidth = 0;
            for (int i = 1; i < super.Text.length(); i++) {
                CurrentTextLineWidth += G.getFontMetrics(super.DrawFont).charWidth(super.Text.charAt(i - 1));

                // check to see if the width is now wider than the text box is wide
                if (CurrentTextLineWidth >= Dimensions.GetValue(Vector2.Dimensions.X)) {
                    CurrentTextLineWidth = 0;

                    DrawStrings.add( super.Text.substring(SplitIndex, super.Text.indexOf(" ", i)));

                    FromIndex = 0;
                    for (String Str : DrawStrings) {
                        FromIndex += Str.length();
                    }

                    // trim space characters off the start and end
                    DrawStrings.set(DrawStrings.size() - 1, DrawStrings.get(DrawStrings.size() - 1).trim());

                    //From index should be the total sum of all the strings in draw strings
                    SplitIndex = super.Text.indexOf(' ', FromIndex);
                }
            }

            // if the DrawStrings is still empty add the original text
            if (DrawStrings.isEmpty()) {
                DrawStrings.add(super.Text);
            }

        } else { // if we do not care about dimensions still add the original text to this list
            DrawStrings.add(super.Text);
        }



        int CurrentHeight = 0;
        for (int i = 0; i < DrawStrings.size(); i++) {
            CurrentHeight += G.getFontMetrics().getHeight();
            if (!DoDrawOutSideDimensions && CurrentHeight > Dimensions.GetValue(Vector2.Dimensions.Y)) {
                break; // done drawing
            }

            // will add text alignment code later, right now it all will be aligned to the left
            G.drawString(DrawStrings.get(i), super.OriginPoint.GetValue(Vector2.Dimensions.X), super.OriginPoint.GetValue(Vector2.Dimensions.Y) + (G.getFontMetrics().getHeight() * (i + 1)));
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
