package me.oscarcusick.main.Engine.Utility;

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

}
