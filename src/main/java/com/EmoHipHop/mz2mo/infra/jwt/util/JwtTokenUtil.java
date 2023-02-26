package com.EmoHipHop.mz2mo.infra.jwt.util;

import java.util.Map;

public interface JwtTokenUtil {
    Map<String, String> decode(String token);
}
