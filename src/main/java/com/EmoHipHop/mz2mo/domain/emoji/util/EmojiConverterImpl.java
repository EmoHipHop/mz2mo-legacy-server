package com.EmoHipHop.mz2mo.domain.emoji.util;

import com.EmoHipHop.mz2mo.domain.emoji.data.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiListResponse;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmojiConverterImpl implements EmojiConverter{
    @Override
    public EmojiListResponse toResponse(List<EmojiDto> emojis) {
        List<EmojiResponse> response = emojis.stream().map(this::toResponse).toList();
        return new EmojiListResponse(response);
    }

    private EmojiResponse toResponse(EmojiDto emoji) {
        return new EmojiResponse(
            emoji.id(),
            emoji.name(),
            emoji.code(),
            emoji.canUse()
        );
    }
}
