package com.EmoHipHop.mz2mo.infra.jwt.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DummyJwtTokenUtil implements JwtTokenUtil {
    @Override
    public Map<String, String> decode(String token) {
        return Map.ofEntries(
                Map.entry("type", "login-access"),
                Map.entry("userId", "mz2mo:user:test"),
                Map.entry("expiration", "false")
        );
    }
}
