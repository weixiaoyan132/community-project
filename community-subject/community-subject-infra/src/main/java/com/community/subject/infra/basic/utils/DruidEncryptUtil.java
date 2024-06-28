package com.community.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair= ConfigTools.genKeyPair(512);
            privateKey=keyPair[0];
            publicKey=keyPair[1];
            System.out.println("privateKey:"+privateKey);
            System.out.println("publicKey:"+publicKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String Text) throws Exception {
        return ConfigTools.encrypt(privateKey,Text);
    }
    public static String decrypt(String Text) throws Exception {
        return ConfigTools.decrypt(publicKey,Text);
    }

    public static void main(String[] args) throws Exception {
        String string=encrypt("1326478219qQ");
        String s=decrypt(string);
        System.out.println(string);
        System.out.println(s);
    }
}
