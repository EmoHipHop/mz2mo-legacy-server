package com.EmoHipHop.mz2mo.domain.vote.exception;

public class ExceededMaxVoteException extends RuntimeException {
    public ExceededMaxVoteException(String userId, String musicId) {
        super(String.format("이미 최대 투표수만큼 투표를 했습니다. - userId: %s, musicId: %s", userId, musicId));
    }
}
