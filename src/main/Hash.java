package main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String hash(String msg){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException e){
            System.err.println("SHA-256 isn't supported");
            md = null;
        }
        if(md != null)
        {
            byte[] hash = md.digest(msg.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0x66 & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        else
        {
            throw new Error("The hashing algorithm isn't supported");
        }
    }

}
