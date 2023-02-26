package com.EmoHipHop.mz2mo.domain.vote.controller;

import com.EmoHipHop.mz2mo.domain.vote.exception.DuplicateVotedException;
import com.EmoHipHop.mz2mo.domain.vote.exception.ExceededMaxVoteException;
import com.EmoHipHop.mz2mo.domain.vote.exception.InvalidVoteEmojiException;
import com.EmoHipHop.mz2mo.global.common.data.response.ErrorResponse;
import com.EmoHipHop.mz2mo.global.common.data.type.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MusicEmojiVoteController.class)
public class MusicEmojiVoteControllerAdvice {
    @ExceptionHandler(DuplicateVotedException.class)
    public ResponseEntity<ErrorResponse> handleException(DuplicateVotedException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.DUPLICATE_VOTED));
    }

    @ExceptionHandler(ExceededMaxVoteException.class)
    public ResponseEntity<ErrorResponse> handleException(ExceededMaxVoteException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.EXCEEDED_MAX_VOTE));
    }

    @ExceptionHandler(InvalidVoteEmojiException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidVoteEmojiException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage(), ErrorCode.INVALID_VOTE_EMOJI));
    }
}
