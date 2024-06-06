package com.mts.toyskingdom.service;

public interface SendEmailSv {

    static boolean sendEmail(String email, String subject, String content){
        return true;
    };

    boolean sendEmailOTP(String email);
}
