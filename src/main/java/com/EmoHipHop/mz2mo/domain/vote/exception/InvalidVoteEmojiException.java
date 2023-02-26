package com.EmoHipHop.mz2mo.domain.vote.exception;

import lombok.Getter;

@Getter
public class InvalidVoteEmojiException extends RuntimeException {
    private final String emoji;

    public InvalidVoteEmojiException(String emojiId) {
        super(String.format("투표에 사용할 수 없는 이모지입니다. - %s", emojiId));
        this.emoji = emojiId;
    }
}
