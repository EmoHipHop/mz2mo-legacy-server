package com.EmoHipHop.mz2mo.domain.vote.util;

import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import com.EmoHipHop.mz2mo.domain.vote.data.request.AddVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.response.AddVoteResponse;

import java.util.List;

public interface VoteConverter {
    AddVoteDto toDto(AddVoteRequest request, String userId, String musicId);
    VoteDto toDto(List<MusicEmojiVote> votes);
    AddVoteResponse toResponse(VoteDto dto);
}
