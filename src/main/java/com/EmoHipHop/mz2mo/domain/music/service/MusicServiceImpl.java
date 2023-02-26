package com.EmoHipHop.mz2mo.domain.music.service;

import com.EmoHipHop.mz2mo.domain.music.data.dto.CreateMusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.dto.MusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.entity.Music;
import com.EmoHipHop.mz2mo.domain.music.data.type.SpotifyUriType;
import com.EmoHipHop.mz2mo.domain.music.exception.DuplicateSpotifyUriException;
import com.EmoHipHop.mz2mo.domain.music.exception.MalformedSpotifyUriException;
import com.EmoHipHop.mz2mo.domain.music.repository.MusicRepository;
import com.EmoHipHop.mz2mo.domain.music.util.MusicConverter;
import com.EmoHipHop.mz2mo.global.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicConverter musicConverter;
    private final MusicRepository musicRepository;
    private final DateUtil dateUtil;

    @Override
    public MusicDto createMusic(CreateMusicDto dto) {
        validateCreateMusic(dto);
        String id = generateId();
        LocalDateTime createAt = dateUtil.getNowDateTime();
        Music entity = new Music(id, createAt, dto.spotifyMusicUri(), dto.spotifyArtistUri());
        Music saved = musicRepository.save(entity);
        return musicConverter.toDto(saved);
    }

    private String generateId() {
        return "mz2mo:music:" + UUID.randomUUID();
    }

    private void validateCreateMusic(CreateMusicDto dto) {
        String musicUri = dto.spotifyMusicUri();
        String artistUri = dto.spotifyArtistUri();

        boolean isMusicUriValid = musicUri.startsWith("spotify:track:");
        boolean isArtistUriValid = artistUri.startsWith("spotify:artist:");

        if (!isMusicUriValid) throw new MalformedSpotifyUriException(SpotifyUriType.MUSIC, musicUri);
        if (!isArtistUriValid) throw new MalformedSpotifyUriException(SpotifyUriType.ARTIST, artistUri);

        boolean isMusicUriExists = !musicRepository.existsBySpotifyMusicUri(musicUri);
        if(!isMusicUriExists) throw new DuplicateSpotifyUriException(SpotifyUriType.MUSIC, musicUri);
    }
}
