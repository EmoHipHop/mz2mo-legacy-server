package com.EmoHipHop.mz2mo.domain.emoji.service;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.repository.EmojiRepository;
import com.EmoHipHop.mz2mo.domain.emoji.util.EmojiConverter;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService {
    private final EmojiRepository emojiRepository;
    private final EmojiConverter emojiConverter;

    @Override
    public List<EmojiDto> getAllEmojis(boolean searchOnlyCanUse) {
        return getAllEntities(searchOnlyCanUse).stream().map(emojiConverter::toDto).toList();
    }

    private List<Emoji> getAllEntities(boolean searchOnlyCanUse) {
        if(searchOnlyCanUse) return emojiRepository.findAllByCanUse(true);
        else return emojiRepository.findAll();
    }
}
