package com.EmoHipHop.mz2mo.domain.music.exception;

import com.EmoHipHop.mz2mo.domain.music.data.type.SpotifyUriType;

public class DuplicateSpotifyUriException extends RuntimeException {
    public DuplicateSpotifyUriException(SpotifyUriType uriType, String uri) {
        super(String.format("이미 존재하는 spotify %s입니다. - %s", uriType.getDisplayType(), uri));
    }
}
