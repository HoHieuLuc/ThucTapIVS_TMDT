package com.console.test;

 
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaxMailTest {
    /*
        Cấu hình gửi email 
    */
    // HOST_NAME: Loại dịch vụ mail
    public static final String HOST_NAME = "smtp.gmail.com";
 
    public static final int SSL_PORT = 465; // Cổng để dùng SSL
 
    public static final int TSL_PORT = 587; // Cổng để dùng TSL (Tùy chọn)
 
    public static final String APP_EMAIL = "minhthienmap2020@gmail.com"; // Địa chỉ email của hệ thống 
 
    public static final String APP_PASSWORD = "MinhThien2000"; // Mật khẩu mail
 
    public static final String RECEIVE_EMAIL = "minhthienmap@gmail.com"; // Địa chỉ email người dùng
    public static void main(String[] args) {
 
        // Tạo biến chứa các thuộc tính cấu hình mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port",SSL_PORT);
 
        // Lại tạo session gửi mail và login vào mail hệ thống
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
 
        // Chỗ này soạn email, cái set text nếu được tui sẽ kiếm code html bỏ vô cho đẹp
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECEIVE_EMAIL));
            message.setSubject("Testing Subject");
            message.setText("Welcome to gpcoder.com");
 
            // send message
            Transport.send(message);
 
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}