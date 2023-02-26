package com.EmoHipHop.mz2mo.domain.emoji.util;

import com.EmoHipHop.mz2mo.domain.emoji.data.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiListResponse;

import java.util.List;

public interface EmojiConverter {
    EmojiListResponse toResponse(List<EmojiDto> emojis);
}
