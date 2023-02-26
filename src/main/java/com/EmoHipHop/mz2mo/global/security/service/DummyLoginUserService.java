package com.EmoHipHop.mz2mo.global.security.service;

import org.springframework.stereotype.Service;

@Service
public class DummyLoginUserService implements LoginUserService {
    @Override
    public String getLoginUserId() {
        return "mz2mo:user:test";
    }
}
