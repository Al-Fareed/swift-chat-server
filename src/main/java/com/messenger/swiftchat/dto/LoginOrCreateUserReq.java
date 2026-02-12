package com.messenger.swiftchat.dto;

import lombok.Data;

@Data
public class LoginOrCreateUserReq {
    private String email;
    private String name;
    private String mobile;
}
