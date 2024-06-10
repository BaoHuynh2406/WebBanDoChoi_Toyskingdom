package com.mts.toyskingdom.service;

public interface SendEmailSv {

    static boolean sendEmail(String email, String subject, String content) {
        return true;
    }

    ;

    int sendEmailOTP(String email);

    public boolean verifyOTP(String email, int otp, String password);
}
