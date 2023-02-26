package com.EmoHipHop.mz2mo.global.emoji.exception;

import lombok.Getter;

@Getter
public class EmojiNotFoundException extends RuntimeException {
    private final String emojiId;

    public EmojiNotFoundException(String emojiId) {
        super(String.format("존재하지 않는 이모지입니다. - emojiId: %s", emojiId));
        this.emojiId = emojiId;
    }
}
