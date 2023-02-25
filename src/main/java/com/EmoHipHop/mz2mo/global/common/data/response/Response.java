package com.EmoHipHop.mz2mo.global.common.data.response;

public record Response<T>(String message, T data) { }