package com.EmoHipHop.mz2mo.domain.music.util;

import com.EmoHipHop.mz2mo.domain.music.data.dto.CreateMusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.dto.MusicDto;
import com.EmoHipHop.mz2mo.global.music.data.entity.Music;
import com.EmoHipHop.mz2mo.domain.music.data.request.CreateMusicRequest;
import com.EmoHipHop.mz2mo.domain.music.data.response.CreateMusicResponse;
import org.springframework.stereotype.Component;

@Component
public class MusicConverterImpl implements MusicConverter {
    @Override
    public CreateMusicDto toDto(CreateMusicRequest request) {
        return new CreateMusicDto(request.spotifyMusicUri(), request.spotifyArtistUri());
    }

    @Override
    public MusicDto toDto(Music entity) {
        return new MusicDto(
                entity.getId(),
                entity.getCreateAt(),
                entity.getSpotifyMusicUri(),
                entity.getSpotifyArtistUri()
        );
    }

    @Override
    public CreateMusicResponse toResponse(MusicDto dto) {
        return new CreateMusicResponse(
                dto.id(),
                dto.createAt(),
                dto.spotifyMusicUri(),
                dto.spotifyArtistUri()
        );
    }
}
