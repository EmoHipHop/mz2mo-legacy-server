package com.EmoHipHop.mz2mo.domain.vote.data.response;

public record RemoveVoteResponse(String userId, String musicId, String[] currentVoteEmojiCodes) { }
