package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.service.SendEmailSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class SendEmailSvImpl implements SendEmailSv {


    public static boolean sendEmail(String email, String subject, String content) {

        final String username = "lynguyenhoa102@gmail.com";
        final String password = "ajxbvpfopcatttpr";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });


        String message = "";

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            // Adding text content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);
            multipart.addBodyPart(messageBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
            message = "Gửi email thành công!";
            System.out.println(message);
            return true;
        } catch (MessagingException e) {
            message = "Gửi email thất bại: " + e.getMessage();
            System.out.println(message);
            return false;
        }
    }

    @Override
    public boolean sendEmailOTP(String email) {
        //So sánh với database email đúng không

        //Dúng thì tạo ra mã OTP //Sai thì return

        //Gửi đi mail kèm OTP
        //Lưu lại OTP ở seesison
        String subject = "Khôi phục tài khoản TOYSKINGDOM của bạn";
        String content = "Vui lòng không chia sẽ mã này. \n Mật mã khôi phục: 123456";
        if(!sendEmail(email, subject, content)){
            System.out.println("Loi gui mail");
            return false;
        }
        return true;
    }


}
