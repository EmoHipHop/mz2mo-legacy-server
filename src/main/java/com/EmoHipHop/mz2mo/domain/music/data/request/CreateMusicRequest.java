package com.EmoHipHop.mz2mo.domain.music.data.request;

public record CreateMusicRequest(
        String spotifyMusicUri,
        String spotifyArtistUri
) { }
