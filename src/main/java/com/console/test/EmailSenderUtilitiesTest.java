package com.console.test;

import com.tmdt.utilities.EmailSender;

public class EmailSenderUtilitiesTest {
    public static void main(String[] args) throws Exception {
        String RECEIVE_EMAIL = "minhthienmap@gmail.com";
        String SUBJECT = "Attack of Titan";
        String CONTENT = "Final Session Erren cày nát nhà";
        EmailSender.senderEmail(RECEIVE_EMAIL, SUBJECT, CONTENT);
    }
}
