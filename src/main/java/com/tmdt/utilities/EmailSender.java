package com.tmdt.utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    /*
     * Cấu hình gửi email
     */
    // HOST_NAME: Loại dịch vụ mail
    public static final String HOST_NAME = "smtp.gmail.com";

    public static final int SSL_PORT = 465; // Cổng để dùng SSL

    public static final int TSL_PORT = 587; // Cổng để dùng TSL (Tùy chọn)

    public static final String APP_EMAIL = "minhthienmap2020@gmail.com"; // Địa chỉ email của hệ thống

    public static final String APP_PASSWORD = "MinhThien2000"; // Mật khẩu mail

    public static final String HTML_CODE = "<h3 style='color:green'>Stylist Mail</h3>";

    // Ẩn đi để thành hàm
    // public static final String = "minhthienmap@gmail.com"; // Địa chỉ email người
    // dùng

    public static void senderEmail(String RECEIVE_EMAIL, String SUBJECT, String CONTENT) {
        // Tạo biến chứa các thuộc tính cấu hình mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);

        // Lại tạo session gửi mail và login vào mail hệ thống
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });

        // Lụm trên gpcoder
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(APP_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVE_EMAIL));

            // Ui, đơn giản hơn code trên stackOverFlow
            message.setSubject(SUBJECT);
            String htmlContent = " <h1>Welcome to <a href=\"gpcoder.com\">GP Coder</a></h1> " + CONTENT +
                    "<img src='https://cdn.discordapp.com/attachments/925309182574469143/931512663123566602/keqcopium.png' " 
                    + " width=\"300\" " + " height=\"180\" " + " border=\"0\" " + " alt=\"gpcoder.com\" />";
            message.setContent(htmlContent, "text/html");

            // Gửi email có code HTML nào
            Transport.send(message);

            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

}
