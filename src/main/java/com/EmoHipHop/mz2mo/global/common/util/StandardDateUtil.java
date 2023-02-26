package com.EmoHipHop.mz2mo.global.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StandardDateUtil implements DateUtil {
    @Override
    public LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }
}
