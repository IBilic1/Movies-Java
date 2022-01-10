/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HT-ICT
 */
public class HashUtil {

    private HashUtil() {
    }

    public static String getHashFromString(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedPassword = md.digest(value.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder sb=new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02X ", b));
        }
        
        return sb.toString();

    }

}
