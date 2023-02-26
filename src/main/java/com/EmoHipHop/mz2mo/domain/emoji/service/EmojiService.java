package com.EmoHipHop.mz2mo.domain.emoji.service;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;

import java.util.List;

public interface EmojiService {
    List<EmojiDto> getAllEmojis(boolean searchOnlyCanUse);
}
