package com.EmoHipHop.mz2mo.global.security.data.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    LOGIN_ACCESS("login-access"),
    LOGIN_REFRESH("login-refresh");

    private final String key;
}
