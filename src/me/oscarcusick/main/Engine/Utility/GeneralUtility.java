package me.oscarcusick.main.Engine.Utility;

import me.oscarcusick.main.Math.Vector2;
import me.oscarcusick.main.Math.Vector2.Dimensions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneralUtility {

    public GeneralUtility() {

    }

    // SHA-256 Hash any string
    public String Hash256(String Plaintext) throws NoSuchAlgorithmException {
        MessageDigest HashMD= MessageDigest.getInstance("SHA-256");
        HashMD.update(Plaintext.getBytes());
        byte[] Digest = HashMD.digest();
        { //Converting the digest-byte arrays in to HexString format
            StringBuffer HexDigest = new StringBuffer();
            for (int i = 0; i < Digest.length; i++) {
                HexDigest.append(Integer.toHexString(0xFF & Digest[i]));
            }
            return HexDigest.toString();
        }
    }

    /**
     * returns true if a selected point is within a parsed 2d 'boundary'
     * @param QueryPoint This is the point that will be checked if it's within the boundary
     * @param BoundaryOriginPoint This is where the boundary starts, or where it is in 2d space
     * @param BoundaryDimensions This is how large the boundary is in the X & Y dimension
     * @param WindowHeaderAdjustAmount This is how much to add to the Y-Axis due to the top window bar taking up space creating a desync. 30 is a good number by default
     * @return True if the QueryPoint is within the defined boundary, else false will be returned
     */
    public boolean IsWithinArea(Vector2<Integer> QueryPoint, Vector2<Integer> BoundaryOriginPoint, Vector2<Integer> BoundaryDimensions, int WindowHeaderAdjustAmount) {

        // first we check if the QP is smaller than either origin point, if so return false
        if ((QueryPoint.GetValue(Dimensions.X) < BoundaryOriginPoint.GetValue(Dimensions.X)) || (QueryPoint.GetValue(Dimensions.Y) < (BoundaryOriginPoint.GetValue(Dimensions.Y) + WindowHeaderAdjustAmount))) {
            return false;
        }

        // next we check if QP is larger than BOP + BD. if it is, it is outside the area
        if ((QueryPoint.GetValue(Dimensions.X) > (BoundaryOriginPoint.GetValue(Dimensions.X) + BoundaryDimensions.GetValue(Dimensions.X))) || (QueryPoint.GetValue(Dimensions.Y) > (BoundaryOriginPoint.GetValue(Dimensions.Y) + BoundaryDimensions.GetValue(Dimensions.Y) + WindowHeaderAdjustAmount))) {
            return false;
        }

        return true;
    }

    public boolean IsWithinArea(Vector2<Integer> QueryPoint, Vector2<Integer> BoundaryOriginPoint, Vector2<Integer> BoundaryDimensions, boolean AdjustForWindowHeaderDesync) {
        int AdjustAmt = 0;

        final int DefaultAdjustmentAmount = 30;

        if (AdjustForWindowHeaderDesync) {
            AdjustAmt = DefaultAdjustmentAmount;
        }

        return IsWithinArea(QueryPoint, BoundaryOriginPoint, BoundaryDimensions, AdjustAmt);
    }




}
