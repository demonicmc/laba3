package com.laba3;


import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by root on 30.04.17.
 */
public class MyMath {

    public static String createMD5(String arg){
        String pass = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(arg.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            pass = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return pass;
    }

}
