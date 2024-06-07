package com.mts.toyskingdom.data.dto;

import lombok.Data;

@Data
public class OtpDTO {
    String email;
    String password;
    int otp;
}
