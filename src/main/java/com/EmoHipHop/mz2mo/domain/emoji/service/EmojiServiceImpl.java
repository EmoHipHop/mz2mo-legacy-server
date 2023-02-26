package com.EmoHipHop.mz2mo.domain.emoji.service;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.AddEmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.repository.EmojiRepository;
import com.EmoHipHop.mz2mo.domain.emoji.util.EmojiConverter;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService {
    private final EmojiRepository emojiRepository;
    private final EmojiConverter emojiConverter;

    @Override
    public List<EmojiDto> getAllEmojis(boolean searchOnlyCanUse) {
        return getAllEntities(searchOnlyCanUse).stream().map(emojiConverter::toDto).toList();
    }

    @Override
    public EmojiDto addEmoji(AddEmojiDto emojiDto) {
        String id = generateId();
        Emoji entity = emojiConverter.toEntity(emojiDto, id);
        Emoji savedEntity = emojiRepository.save(entity);
        return emojiConverter.toDto(savedEntity);
    }

    private String generateId() {
        return "mz2mo:emoji:" + UUID.randomUUID();
    }

    private List<Emoji> getAllEntities(boolean searchOnlyCanUse) {
        if(searchOnlyCanUse) return emojiRepository.findAllByCanUse(true);
        else return emojiRepository.findAll();
    }
}
