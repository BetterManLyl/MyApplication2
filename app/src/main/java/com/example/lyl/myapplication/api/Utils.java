package com.example.lyl.myapplication.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lyl
 * @date 2017/11/19.
 */

public class Utils {
    /**
     * 获取MD5值，和后台保持一样,复制过来的
     *
     * @param s 需要转换的字符串
     * @return md5值或者null
     */
    public final static String getMD5(String s) {
        if (s == null) {
            return null;
        }

        String result = null;

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = s.getBytes();

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            result = new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        return result;
    }

}
