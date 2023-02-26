package com.EmoHipHop.mz2mo.global.security.service;

import com.EmoHipHop.mz2mo.domain.user.repository.UserRepository;
import com.EmoHipHop.mz2mo.global.user.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DummyLoginUserService implements LoginUserService {
    private final UserRepository userRepository;

    @Override
    public String getLoginUserId() {
        String userId = "mz2mo:user:" + UUID.randomUUID();
        userRepository.save(new User(userId, "develop.raul@gmail.com", "raul", LocalDateTime.now()));

        return userId;
    }
}
