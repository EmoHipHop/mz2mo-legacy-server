package com.EmoHipHop.mz2mo.domain.vote.data.dto;

public record VoteDto(
    String id,
    String userId,
    String musicId,
    String[] currentVoteEmojiCodes
) { }