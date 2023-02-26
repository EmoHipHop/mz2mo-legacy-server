package com.EmoHipHop.mz2mo.global.emoji.exception;

public class EmojiNotFoundException extends RuntimeException {
    public EmojiNotFoundException(String emojiId) {
        this("emojiId", emojiId);
    }

    public EmojiNotFoundException(String key, String value) {
        super(String.format("존재하지 않는 이모지입니다. - %s: %s", key, value));
    }
}
