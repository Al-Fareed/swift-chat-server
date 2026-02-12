package com.messenger.swiftchat.service;

import com.messenger.swiftchat.dto.LoginOrCreateUserReq;
import com.messenger.swiftchat.model.User;
import com.messenger.swiftchat.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Auth {

    public User loginOrSignUp(LoginOrCreateUserReq req){
        User user = new User();
        user.setEmail(req.getEmail());
        user.setMobile(req.getMobile());
        user.setName(req.getName());
        return user;
    }

    public Optional<User> login(LoginOrCreateUserReq req){
        return UserRepo.findByEmail(req.getEmail()).orElse(()-> loginOrSignUp(req));
    }
}
