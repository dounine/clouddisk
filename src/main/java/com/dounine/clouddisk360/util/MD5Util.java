package com.dounine.clouddisk360.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);
    private static final String ERROR = "error";
    
	private MD5Util(){}
	
    public final static String MD5(final String s) {
        byte[] btInput = null;
        try {
            btInput = s.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(ERROR,e);
        }
        return MD5(btInput);
    }

    public static String string2MD5(final String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            LOGGER.error(ERROR,e);
            return "";
        }
        final char[] charArray = inStr.toCharArray();
        final byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        final byte[] md5Bytes = md5.digest(byteArray);
        final StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            final int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append('0');
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    public final static String MD5(final byte[] data) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ERROR,e);
        }
        final byte[] byteArray = messageDigest.digest();
        final StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append('0').append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(final String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);

    }
}
