package com.example.myvulnerableapplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : Utils
Purpose: The purpose of this class is to have all the utils that multiple classes are going to use. In this case this class has the methods
to hash the strings with SHA-256
 */
public class Utils {
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : bytesToHexString
    Purpose: The purpose of this method is to convert the String bytes to Hexadecimal String
    */
    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : hash256
    Purpose: The purpose of this method is to hash a String using SHA-256
    */
    public String hash256(String password){
        MessageDigest digest=null;
        String hash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            hash = bytesToHexString(digest.digest());
        } catch(NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return hash;
    }
}
