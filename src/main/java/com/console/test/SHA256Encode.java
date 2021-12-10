package com.console.test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import com.google.common.hash.Hashing;

public class SHA256Encode {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        String originalString = "123";
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        System.out.println("Hash: " + sha256hex);

        //Lưu password và kiểm tra password bằng cách này luôn
        
    }
}
