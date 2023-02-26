package com.EmoHipHop.mz2mo.domain.vote.exception;

public class DuplicateVotedException extends RuntimeException {
    public DuplicateVotedException(String userId, String musicId, String emojiId) {
        super(String.format("이미 투표했습니다. - userId: %s, musicId: %s, emojiId: %s", userId, musicId, emojiId));
    }
}
