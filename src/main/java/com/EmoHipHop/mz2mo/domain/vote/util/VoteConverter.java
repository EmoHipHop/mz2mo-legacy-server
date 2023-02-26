package com.EmoHipHop.mz2mo.domain.vote.util;

import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.RemoveVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import com.EmoHipHop.mz2mo.domain.vote.data.request.AddVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.request.RemoveVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.response.AddVoteResponse;
import com.EmoHipHop.mz2mo.domain.vote.data.response.RemoveVoteResponse;

import java.util.List;

public interface VoteConverter {
    AddVoteDto toDto(AddVoteRequest request, String userId, String musicId);
    RemoveVoteDto toDto(RemoveVoteRequest request, String userId, String musicId);
    VoteDto toDto(List<MusicEmojiVote> votes, String s, String musicId);
    AddVoteResponse toAddResponse(VoteDto dto);
    RemoveVoteResponse toRemoveResponse(VoteDto dto);

}
