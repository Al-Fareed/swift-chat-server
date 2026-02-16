package com.messenger.swiftchat.repo;

import com.messenger.swiftchat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo
        extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);

     Optional<User> findById(String id);
}
