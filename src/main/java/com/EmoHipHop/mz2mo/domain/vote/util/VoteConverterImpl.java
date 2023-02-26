package com.EmoHipHop.mz2mo.domain.vote.util;

import com.EmoHipHop.mz2mo.domain.emoji.repository.EmojiRepository;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.RemoveVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import com.EmoHipHop.mz2mo.domain.vote.data.request.AddVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.request.RemoveVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.response.AddVoteResponse;
import com.EmoHipHop.mz2mo.domain.vote.data.response.RemoveVoteResponse;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VoteConverterImpl implements VoteConverter {
    private final EmojiRepository emojiRepository;

    @Override
    public AddVoteDto toDto(AddVoteRequest request, String userId, String musicId) {
        return new AddVoteDto(
            userId,
            musicId,
            emojiRepository.findByCode(request.emojiCode()).getId()
        );
    }

    @Override
    public RemoveVoteDto toDto(RemoveVoteRequest request, String userId, String musicId) {
        return new RemoveVoteDto(
                userId,
                musicId,
                emojiRepository.findByCode(request.emojiCode()).getId()
        );
    }

    @Override
    public VoteDto toDto(List<MusicEmojiVote> votes) {
        MusicEmojiVote vote = votes.get(0);
        return new VoteDto(
                vote.getId(),
                vote.getUser().getId(),
                vote.getMusic().getId(),
                votes.stream().map(MusicEmojiVote::getEmoji).map(Emoji::getId).toArray(String[]::new)
        );
    }

    @Override
    public AddVoteResponse toAddResponse(VoteDto dto) {
        return new AddVoteResponse(
            dto.userId(),
            dto.musicId(),
            dto.currentVoteEmojiIds()
        );
    }

    @Override
    public RemoveVoteResponse toRemoveResponse(VoteDto dto) {
        return new RemoveVoteResponse(
                dto.userId(),
                dto.musicId(),
                dto.currentVoteEmojiIds()
        );
    }
}