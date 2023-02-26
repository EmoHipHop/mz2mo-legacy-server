package com.EmoHipHop.mz2mo.global.common.controller;

import com.EmoHipHop.mz2mo.global.common.data.response.ErrorResponse;
import com.EmoHipHop.mz2mo.global.common.data.type.ErrorCode;
import com.EmoHipHop.mz2mo.global.emoji.exception.EmojiNotFoundException;
import com.EmoHipHop.mz2mo.global.music.exception.MusicNotFoundException;
import com.EmoHipHop.mz2mo.global.user.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundAdvice {
    @ExceptionHandler(EmojiNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EmojiNotFoundException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.EMOJI_NOT_FOUND));
    }

    @ExceptionHandler(MusicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(MusicNotFoundException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.MUSIC_NOT_FOUND));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.USER_NOT_FOUND));
    }
}
