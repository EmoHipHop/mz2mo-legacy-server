package com.EmoHipHop.mz2mo.global.music.exception;

import lombok.Getter;

@Getter
public class MusicNotFoundException extends RuntimeException {
    private final String musicId;

    public MusicNotFoundException(String musicId) {
        super(String.format("존재하지 않는 노래입니다. - musicId: %s", musicId));
        this.musicId = musicId;
    }
}
