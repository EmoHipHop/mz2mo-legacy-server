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
import com.EmoHipHop.mz2mo.global.emoji.exception.EmojiNotFoundException;
import com.EmoHipHop.mz2mo.global.emoji.util.EmojiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VoteConverterImpl implements VoteConverter {
    private final EmojiRepository emojiRepository;

    @Override
    public AddVoteDto toDto(AddVoteRequest request, String userId, String musicId) {
        String rawEmoji = request.rawEmoji();
        String emojiCode = EmojiUtil.serialize(rawEmoji);
        return new AddVoteDto(
            userId,
            musicId,
            emojiRepository.findByCode(emojiCode).orElseThrow(() -> new EmojiNotFoundException("emojiId", emojiCode)).getId()
        );
    }

    @Override
    public RemoveVoteDto toDto(RemoveVoteRequest request, String userId, String musicId) {
        String rawEmoji = request.rawEmoji();
        String emojiCode = EmojiUtil.serialize(rawEmoji);
        return new RemoveVoteDto(
                userId,
                musicId,
                emojiRepository.findByCode(emojiCode)
                        .orElseThrow(() -> new EmojiNotFoundException("emojiId", emojiCode))
                        .getId()
        );
    }

    @Override
    public VoteDto toDto(List<MusicEmojiVote> votes, String userId, String musicId) {
        return new VoteDto(
                userId,
                musicId,
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