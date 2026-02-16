package com.messenger.swiftchat.service;

import com.messenger.swiftchat.dto.SendOtpRequest;
import com.messenger.swiftchat.dto.VerifyOtpRequest;
import com.messenger.swiftchat.model.User;
import com.messenger.swiftchat.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Auth {
    private final UserRepo userRepo;

    public Auth(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String sendOTP(SendOtpRequest otpRequest){
        String email = otpRequest.getEmail();
        User userRes = userRepo.findByEmail(email)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(email);
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

        if (user.getOtp() == null ||
                !user.getOtp().equals(req.getOtp())) {

            throw new RuntimeException(
                    "OTP Verification failed");
        }
        user.setOtp(null);
        user.setVerified(true);
        user.setUserLoggedIn(true);
        userRepo.save(user);
        return "OTP Verified Successfully";
    }

    public String logoutUser(String id){
        User user = userRepo.findById(id);
        user.setUserLoggedIn(false);
        userRepo.save(user);
        return "User logged out successfully";
    }
}
