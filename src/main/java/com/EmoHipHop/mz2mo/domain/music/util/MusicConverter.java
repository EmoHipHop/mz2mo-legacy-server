package com.EmoHipHop.mz2mo.domain.music.util;

import com.EmoHipHop.mz2mo.domain.music.data.dto.CreateMusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.dto.MusicDto;
import com.EmoHipHop.mz2mo.global.music.data.entity.Music;
import com.EmoHipHop.mz2mo.domain.music.data.request.CreateMusicRequest;
import com.EmoHipHop.mz2mo.domain.music.data.response.CreateMusicResponse;

public interface MusicConverter {
    CreateMusicDto toDto(CreateMusicRequest request);
    MusicDto toDto(Music entity);
    CreateMusicResponse toResponse(MusicDto dto);
}
