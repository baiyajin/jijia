package com.baiyajin.systemuser.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtils {
    public static String enPassword(String password){
        String salt = HashSalt.encode(Long.parseLong(password));
        String hashSalt = HashSalt.getMD5(salt);
        String ecPassWord = new SimpleHash("SHA-1", password, hashSalt).toString();
        return ecPassWord;
    }
}
