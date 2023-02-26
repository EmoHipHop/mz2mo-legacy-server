package com.EmoHipHop.mz2mo.domain.music.data.response;

import java.time.LocalDateTime;

public record CreateMusicResponse(String musicId, LocalDateTime createAt, String spotifyMusicUri, String spotifyArtistUri) { }