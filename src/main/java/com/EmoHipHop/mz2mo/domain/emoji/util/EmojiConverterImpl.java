package com.EmoHipHop.mz2mo.domain.emoji.util;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.AddEmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.request.BulkCreateEmojiRequest;
import com.EmoHipHop.mz2mo.domain.emoji.data.request.CreateEmojiRequest;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiListResponse;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiResponse;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import com.EmoHipHop.mz2mo.global.emoji.util.EmojiUtil;
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

    @Override
    public AddEmojiDto toDto(CreateEmojiRequest request) {
        String code = EmojiUtil.serialize(request.rawEmoji());
        return new AddEmojiDto(
                code,
                request.name(),
                request.canUse()
        );
    }

    @Override
    public EmojiResponse toResponse(EmojiDto emoji) {
        String rawEmoji = EmojiUtil.deserialize(emoji.code());
        return new EmojiResponse(
            emoji.id(),
            emoji.name(),
            rawEmoji,
            emoji.canUse()
        );
    }

    @Override
    public Emoji toEntity(AddEmojiDto emojiDto, String id) {
        return new Emoji(
                id,
                emojiDto.code(),
                emojiDto.name(),
                emojiDto.canUse()
        );
    }

    @Override
    public List<AddEmojiDto> toDto(BulkCreateEmojiRequest request) {
        return request.list().stream().map(this::toDto).toList();
    }
}
