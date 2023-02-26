package com.EmoHipHop.mz2mo.global.vote.data.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public final class VoteUpdateEvent {
    private final String userId;
    private final String musicId;
}
