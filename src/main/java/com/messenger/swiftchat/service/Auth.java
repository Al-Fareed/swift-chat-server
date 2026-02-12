package com.messenger.swiftchat.service;

import com.messenger.swiftchat.dto.SendOtpRequest;
import com.messenger.swiftchat.dto.VerifyOtpRequest;
import com.messenger.swiftchat.model.User;
import com.messenger.swiftchat.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Auth {
    private final UserRepo userRepo;
    public String sendOTP(SendOtpRequest otpRequest){
        User userRes = userRepo.findByEmail(otpRequest.getEmail())
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(otpRequest.getEmail());
                    user.setVerified(false);
                    return user;
                });
        String otp = "1111";
                userRes.setOtp(otp);
                userRepo.save(userRes);
        return "OTP sent successfully";
    }

    public String verifyOTP(VerifyOtpRequest req){
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getOtp().equals(req.getOtp())) throw new RuntimeException("OTP Verification failed");

        user.setOtp(null);
        user.setVerified(true);
        userRepo.save(user);
        return "OTP Verified Successfully";
    }
}
