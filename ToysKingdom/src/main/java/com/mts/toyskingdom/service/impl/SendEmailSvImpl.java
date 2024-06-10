package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.UserDTO;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.service.SendEmailSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SendEmailSvImpl implements SendEmailSv {

    final UserSvlmpl userSvlmpl;

    private Map<String, Integer> otpStorage = new ConcurrentHashMap<>();

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
            System.out.println("Gửi email thành công!");
            return true;
        } catch (MessagingException e) {
            System.out.println("Gửi email thất bại: " + e.getMessage());
            return false;
        }
    }

    @Override
    public int sendEmailOTP(String email) {
        // So sánh với database email
        List<UserM> user = userSvlmpl.getUserByEmail(email);
        if (user.isEmpty()) {
            System.out.println("false");
            return -1;
        }

        // Tạo mã 6 số ngẫu nhiên
        Random random = new Random();
        int randomCode = 100000 + random.nextInt(900000);
        otpStorage.put(email, randomCode); // Lưu mã OTP vào Map với email là key
        // Gửi đi mail kèm OTP
        String subject = "Khôi phục tài khoản TOYSKINGDOM của bạn";
        String content = "Vui lòng không chia sẻ mã này. \n Mật mã khôi phục: " + randomCode;
        if (!sendEmail(email, subject, content)) {
            System.out.println("Lỗi gửi mail");
            return -1;
        }
        return randomCode;
    }


    public boolean verifyOTP(String email, int otp, String password) {
        Integer storedOtp = otpStorage.get(email);
        System.out.println("OTP NHAP: " + otp);
        System.out.println("OTP: " + storedOtp);
        if (storedOtp != null && storedOtp == otp) {
            otpStorage.remove(email); // Xóa OTP sau khi xác thực thành công
            try {
                //Cho phep doi mat
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(email);
                userDTO.setPassword(password);
                int kq = userSvlmpl.saveUser(userDTO);
                return true;
            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }
        return false;
    }
}
