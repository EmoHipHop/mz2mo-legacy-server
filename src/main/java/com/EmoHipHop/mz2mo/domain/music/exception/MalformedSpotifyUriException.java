package com.EmoHipHop.mz2mo.domain.music.exception;

import com.EmoHipHop.mz2mo.domain.music.data.type.SpotifyUriType;

public class MalformedSpotifyUriException extends RuntimeException {
    public MalformedSpotifyUriException(SpotifyUriType uriType, String uri) {
        super(String.format("유효하지 않은 spotify %s입니다. - %s", uriType.getDisplayType(), uri));
    }
}
