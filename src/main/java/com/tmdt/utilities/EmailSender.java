package com.tmdt.utilities;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

    public static final String HTML_CODE = "<h3 style='color:green'>Stylist Mail</h3>";
 
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
            /** 
             * Chỗ này cấu hình nội dung mail định dạng HTML
             * **/
            MimeMessage message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            // Text part là nội dung đơn thuần
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(CONTENT,"utf-8");

            // Htmlpart là phần code html
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(HTML_CODE,"text/html; charset=UTF-8");

            //Thứ tự chèn, code html trên và nội dung phía dưới, tương lai sẽ lồng  nội dung trong div html
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(textPart);

            //Đọc email nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECEIVE_EMAIL));
            //Tiêu đề
            message.setSubject(SUBJECT);
            // Nội dùng kèm code HTML
            message.setContent(multipart);
            // Nội dung text đơn thuần
            message.setText(CONTENT);
 
            // Gửi email
            Transport.send(message);
 
            System.out.println("Gửi mail thành công");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
}
