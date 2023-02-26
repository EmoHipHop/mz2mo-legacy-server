package com.EmoHipHop.mz2mo.global.user.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String userId;

    public UserNotFoundException(String userId) {
        super(String.format("존재하지 않는 사용자입니다. - userId: %s", userId));
        this.userId = userId;
    }
}
