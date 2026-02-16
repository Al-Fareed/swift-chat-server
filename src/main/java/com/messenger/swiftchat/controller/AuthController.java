package com.messenger.swiftchat.controller;

import com.messenger.swiftchat.dto.SendOtpRequest;
import com.messenger.swiftchat.dto.VerifyOtpRequest;
import com.messenger.swiftchat.service.Auth;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final Auth authService;

    public AuthController(Auth authService) {
        this.authService = authService;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody SendOtpRequest req){
        return  authService.sendOTP(req);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest req){
        return authService.verifyOTP(req);
    }

    @GetMapping("/logout/{id}")
    public String userLogout(@PathVariable Long id){
        return authService.logoutUser(id);
    }
}
