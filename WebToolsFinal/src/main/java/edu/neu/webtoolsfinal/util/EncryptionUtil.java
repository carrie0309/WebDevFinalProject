/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptionUtil {

    public static String encryptPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        StringBuilder sb = new StringBuilder();
        for (byte b : thedigest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
