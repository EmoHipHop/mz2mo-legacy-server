package com.EmoHipHop.mz2mo.domain.vote.util;

import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.request.AddVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.response.AddVoteResponse;
import org.springframework.stereotype.Component;

@Component
public class VoteConverterImpl implements VoteConverter {

    @Override
    public AddVoteDto toDto(AddVoteRequest request, String userId, String musicId) {
        return new AddVoteDto(
            userId,
            musicId,
            request.emojiCode()
        );
    }

    @Override
    public AddVoteResponse toResponse(VoteDto dto) {
        return new AddVoteResponse(
            dto.userId(),
            dto.musicId(),
            dto.currentVoteEmojiCodes()
        );
    }
}