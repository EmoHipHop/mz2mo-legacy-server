package com.EmoHipHop.mz2mo.domain.music.data.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpotifyUriType {
    MUSIC("music uri"),
    ARTIST("artist uri");

    private final String displayType;
}
