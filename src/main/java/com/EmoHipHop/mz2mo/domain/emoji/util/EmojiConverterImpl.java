package com.EmoHipHop.mz2mo.domain.emoji.util;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiListResponse;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiResponse;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmojiConverterImpl implements EmojiConverter {
    @Override
    public EmojiListResponse toResponse(List<EmojiDto> emojis) {
        List<EmojiResponse> response = emojis.stream().map(this::toResponse).toList();
        return new EmojiListResponse(response);
    }

    @Override
    public EmojiDto toDto(Emoji entity) {
        return new EmojiDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.isCanUse()
        );
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
