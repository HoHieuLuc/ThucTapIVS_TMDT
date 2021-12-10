package com.console.test;

import org.mindrot.jbcrypt.BCrypt;

public class JBCryptTest {
    public static void main(String[] args) throws Exception {
        String password = "thien-luc";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println("BCrypt hash: " + hash);

        if (BCrypt.checkpw(password, hash)) {
            System.out.println("Hash and password matches"); 
        }
        else System.out.println("Hash and password does not match");
    }
}
