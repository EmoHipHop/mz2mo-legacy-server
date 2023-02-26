package com.EmoHipHop.mz2mo.domain.music.controller;

import com.EmoHipHop.mz2mo.domain.music.exception.DuplicateSpotifyUriException;
import com.EmoHipHop.mz2mo.domain.music.exception.MalformedSpotifyUriException;
import com.EmoHipHop.mz2mo.global.common.data.response.ErrorResponse;
import com.EmoHipHop.mz2mo.global.common.data.type.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MusicController.class)
public class MusicControllerAdvice {
    @ExceptionHandler(MalformedSpotifyUriException.class)
    public ResponseEntity<ErrorResponse> handleMalformedSpotifyUriException(MalformedSpotifyUriException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.MALFORMED_SPOTIFY_URI));
    }

    @ExceptionHandler(DuplicateSpotifyUriException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateSpotifyUriException(DuplicateSpotifyUriException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.DUPLICATE_SPOTIFY_URI));
    }
}
