package com.EmoHipHop.mz2mo.domain.music.service;

import com.EmoHipHop.mz2mo.domain.music.data.dto.CreateMusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.dto.MusicDto;

public interface MusicService {
    MusicDto createMusic(CreateMusicDto dto);
}
