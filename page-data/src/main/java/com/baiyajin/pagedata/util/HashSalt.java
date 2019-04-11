package com.baiyajin.pagedata.util;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;
import java.util.Stack;

/**
 * @author A
 */
public class HashSalt {
    public static void main(String[] args) {
        String salt = HashSalt.encode(Long.parseLong("15288102051"));
        String hashSalt = HashSalt.getMD5(salt);
        String ecPassWord = new SimpleHash("SHA-1", "love", hashSalt).toString();

        System.out.println("---ecPassWord--------" + ecPassWord);
        System.out.println("---hashSalt--------" + hashSalt);
        System.out.println("---salt--------" + salt);

        System.out.println("密文：" + encode(15288102051L));
        System.out.println("原文：" + decode(encode(15288102051L)));
        System.out.println("原文：" + decode(salt));
    }

    /**
     * 密文加密和解析字典，必须private，可以根据需要打乱这些字符的顺序，打乱后，可以得到不同的密码，最好按需打乱
     */
    private static final char[] array = { 'M', 'w', 'N', 'n', '8', '2', 'u', '3', 'o', '-', 'a', 's', 'L', 'f', 'Y',
            'h', 'W', 'J', 'l', 'z', 'x', 'c', 'v', 'b', 'r', 'm', 't', '5', 'y', '7', 'i', '6', 'k', '0', 'A', 'C',
            'Q', 'j', 'E', 'O', 'T', 'g', 'U', 'I', 'R', 'P', '9', 'S', 'D', 'F', 'G', 'H', '4', 'K', 'd', '+', 'X',
            '1', 'V', 'B', 'e', 'q', 'Z', 'p' };

    /**
     * @param number
     *            long类型的10进制数,该数必须大于0
     * @return string类型的密文
     */
    public static String encode(Long number) {
        Long rest = number;
        // 创建栈
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest >= 1) {
            // 进栈,
            // 也可以使用(rest - (rest / 64) * 64)作为求余算法
            stack.add(array[new Long(rest % 64).intValue()]);
            rest = rest / 64;
        }
        for (; !stack.isEmpty();) {
            // 出栈
            result.append(stack.pop());
        }
        return result.toString();

    }

    /**
     * 支持范围是A-Z,a-z,0-9,+,-
     *
     * @param str
     * @return
     */
    public static long decode(String str) {
        // 倍数
        int multiple = 1;
        Long result = 0L;
        Character c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(str.length() - i - 1);
            result += decodeChar(c) * multiple;
            multiple = multiple * 64;
        }
        return result;
    }

    private static int decodeChar(Character c) {
        for (int i = 0; i < array.length; i++) {
            if (c == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 哈希盐
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}