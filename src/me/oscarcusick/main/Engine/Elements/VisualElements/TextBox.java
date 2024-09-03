package me.oscarcusick.main.Engine.Elements.VisualElements;

import me.oscarcusick.main.Engine.Math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class TextBox extends TextElement {

    private Graphics2D G2;

    public enum CenteringTypes {
        Left,
        Center
    }

    Vector2<Integer> Dimensions;
    private boolean DoTextWrap = true, DoDrawOutSideDimensions = false;
    private int DrawCenteringType = CenteringTypes.Left.ordinal();

    // weather to draw the 'literal bounding box' lol
    private boolean DrawBoundingBox = false;
    private Color BoundingBoxColour = new Color(0, 0, 0, 127);


    public TextBox(Graphics2D G2, Vector2<Integer> OriginPoint, Vector2<Integer> Dimensions) {
        super(G2, OriginPoint, new Font("Cascadia Code Regular", Font.BOLD, 20), "", Color.white); // slap in some default values that there will be methods to change
        this.G2 = G2;
        this.Dimensions = Dimensions;
    }

    private int ReCalculateFromIndex(ArrayList<String> DrawStrings) {
        int FromIndex = 0;
        for (String Str : DrawStrings) {
            FromIndex += Str.length();
        }
        return FromIndex;
    }

    private int GetStringWidth(Font Font, String String) {
        int ReturnValue = 0;
        for (char C : String.toCharArray()) {
            ReturnValue += G.getFontMetrics(Font).charWidth(C);
        }
        return ReturnValue;
    }

    // this entire function is messy due to the fact there were many bugs. it is now in a 'functional' state.
    // if more work needs to be done to this class i will re-factor this function and take a lot of the code and put it into separate methods for later
    // maybe ill add padding, idk
    @Override
    public void Draw() {
        // set font to DrawFont
        G2.setFont(DrawFont);

        // if text wrapping all the split lines will be separate strings in here
        ArrayList<String> DrawStrings = new ArrayList<>();

        // make sure it ends in space for proper splitting
        if (!super.Text.endsWith(" ")) {
            super.Text += " ";
        }

        if (DoTextWrap) {
            int SplitIndex = 0, FromIndex;

            int CurrentTextLineWidth = 0;
            for (int i = 1; i < super.Text.length() + 1; i++) {
                CurrentTextLineWidth += G.getFontMetrics(super.DrawFont).charWidth(super.Text.charAt(i - 1));

                // check to see if the width is now wider than the text box is wide
                // WILL NOT ADD THE LAST STRING FOR THE TEXT WRAP IF ITS LESS THAT DIMENSIONS X
                if (CurrentTextLineWidth >= Dimensions.GetValue(Vector2.Dimensions.X)) {
                    CurrentTextLineWidth = 0;


                    DrawStrings.add( super.Text.substring(SplitIndex, super.Text.indexOf(" ", i)));

                    FromIndex = ReCalculateFromIndex(DrawStrings);

                    // trim space characters off the start and end
                    DrawStrings.set(DrawStrings.size() - 1, DrawStrings.get(DrawStrings.size() - 1).trim());

                    // once we have split the text, it still may be longer than the width of the box.
                    // we now need to remove the last word if that is the case
                    {
                        int CurrentStringSize = 0;

                        for (char c : DrawStrings.get(DrawStrings.size() - 1).toCharArray()) {
                            CurrentStringSize += G.getFontMetrics(super.DrawFont).charWidth(c);
                        }

                        // if the string is outside the box, we need to remove it and add it to the next line
                        if (CurrentStringSize > Dimensions.GetValue(Vector2.Dimensions.X)) {
                            // remove the last word
                            DrawStrings.set(DrawStrings.size() - 1, DrawStrings.get(DrawStrings.size() - 1).substring(0, DrawStrings.get(DrawStrings.size() - 1).lastIndexOf(' ')));

                            // add the word to the next line
                            // we do this by re-calculating where to start the text wrap
                            FromIndex = ReCalculateFromIndex(DrawStrings);
                        }

                    }


                    //From index should be the total sum of all the strings in draw strings
                    SplitIndex = super.Text.indexOf(' ', FromIndex);
                    continue; // don't check the next condition in the loop
                }
                // if the last 'wrap-string' is smaller than the x-axis, we get it here
                else if (i == super.Text.length()){
                    DrawStrings.add( super.Text.substring(SplitIndex, i).trim()); // here
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

            if (this.DrawCenteringType == CenteringTypes.Left.ordinal()) {
                G.drawString(DrawStrings.get(i), super.OriginPoint.GetValue(Vector2.Dimensions.X), super.OriginPoint.GetValue(Vector2.Dimensions.Y) + (G.getFontMetrics().getHeight() * (i + 1)));
            }

            else if (this.DrawCenteringType == CenteringTypes.Center.ordinal()) {
                // get string width
                int CurrentWidth = GetStringWidth(this.DrawFont, DrawStrings.get(i));

                // get where to start drawing
                int XDrawPos = (this.OriginPoint.GetValue(Vector2.Dimensions.X) + (((this.Dimensions.GetValue(Vector2.Dimensions.X) - CurrentWidth )/ 2)));

                G.drawString(DrawStrings.get(i), XDrawPos, super.OriginPoint.GetValue(Vector2.Dimensions.Y) + (G.getFontMetrics().getHeight() * (i + 1)));
            }
        }

        if (DrawBoundingBox) {
            G2.setColor(BoundingBoxColour);
            G2.setStroke(new BasicStroke(2));
            G2.drawRoundRect(super.OriginPoint.GetValue(Vector2.Dimensions.X), super.OriginPoint.GetValue(Vector2.Dimensions.Y), Dimensions.GetValue(Vector2.Dimensions.X), Dimensions.GetValue(Vector2.Dimensions.Y), 10, 10);
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

    public void SetDrawBoundingBox(boolean DoDrawBoundingBox) {
        this.DrawBoundingBox = DoDrawBoundingBox;
    }

    public void SetBoundingBoxColour(Color NewColour) {
        this.BoundingBoxColour = NewColour;
    }

    public void SetDrawCenteringType(CenteringTypes NewCenteringType) {
        this.DrawCenteringType = NewCenteringType.ordinal();
    }
    public CenteringTypes GetDrawCenteringType() {
        switch (DrawCenteringType) {
            case 0:
                return CenteringTypes.Left;
            case 1:
                return CenteringTypes.Center;
            default:
                return CenteringTypes.Left;
        }
    }

}
