package com.EmoHipHop.mz2mo.global.common.data.response;

import com.EmoHipHop.mz2mo.global.common.data.type.ErrorCode;

public record ErrorResponse(String message, ErrorCode code) { }