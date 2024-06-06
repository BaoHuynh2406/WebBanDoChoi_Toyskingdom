package com.mts.toyskingdom.api;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void guimail(String nguoinhan, String NDemail) {

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

        String emailTo = nguoinhan;
        String emailSubject = "Mật mã khôi phục";
        String emailContent = NDemail;
        String message = "";

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mimeMessage.setSubject(emailSubject);

            Multipart multipart = new MimeMultipart();

            // Adding text content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailContent);
            multipart.addBodyPart(messageBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
            message = "Gửi email thành công!";
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
            message = "Gửi email thất bại: " + e.getMessage();
        }
    }
}
