package com.EmoHipHop.mz2mo.domain.music.data.dto;

import java.time.LocalDateTime;

public record MusicDto(String id, LocalDateTime createAt, String spotifyMusicUri, String spotifyArtistUri) { }