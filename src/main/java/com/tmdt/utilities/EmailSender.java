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
        Cấu hình gửi email 
    */
    // HOST_NAME: Loại dịch vụ mail
    public static final String HOST_NAME = "smtp.gmail.com";
 
    public static final int SSL_PORT = 465; // Cổng để dùng SSL
 
    public static final int TSL_PORT = 587; // Cổng để dùng TSL (Tùy chọn)
 
    public static final String APP_EMAIL = "minhthienmap2020@gmail.com"; // Địa chỉ email của hệ thống 
 
    public static final String APP_PASSWORD = "MinhThien2000"; // Mật khẩu mail
 
    //Ẩn đi để thành hàm
    //public static final String  = "minhthienmap@gmail.com"; // Địa chỉ email người dùng

    public static void  senderEmail(String RECEIVE_EMAIL,String SUBJECT, String CONTENT) {
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
 
        //TODO: Chỗ này soạn email, cái set text nếu được tui sẽ kiếm code html bỏ vô cho đẹp (MimeBodyPart)
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECEIVE_EMAIL));
            message.setSubject(SUBJECT);
            message.setText(CONTENT);
 
            // Gửi email
            Transport.send(message);
 
            System.out.println("Gửi mail thành công");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
}
