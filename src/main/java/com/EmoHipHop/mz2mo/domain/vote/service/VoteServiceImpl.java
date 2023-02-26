package com.EmoHipHop.mz2mo.domain.vote.service;

import com.EmoHipHop.mz2mo.domain.emoji.repository.EmojiRepository;
import com.EmoHipHop.mz2mo.domain.music.repository.MusicRepository;
import com.EmoHipHop.mz2mo.domain.user.repository.UserRepository;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import com.EmoHipHop.mz2mo.domain.vote.exception.DuplicateVotedException;
import com.EmoHipHop.mz2mo.domain.vote.exception.ExceededMaxVoteException;
import com.EmoHipHop.mz2mo.domain.vote.exception.InvalidVoteEmojiException;
import com.EmoHipHop.mz2mo.domain.vote.repository.MusicEmojiVoteRepository;
import com.EmoHipHop.mz2mo.domain.vote.util.VoteConverter;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import com.EmoHipHop.mz2mo.global.emoji.exception.EmojiNotFoundException;
import com.EmoHipHop.mz2mo.global.music.data.entity.Music;
import com.EmoHipHop.mz2mo.global.music.exception.MusicNotFoundException;
import com.EmoHipHop.mz2mo.global.user.data.entity.User;
import com.EmoHipHop.mz2mo.global.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private static final int MAX_VOTE_COUNT = 3;
    private final VoteConverter voteConverter;
    private final MusicEmojiVoteRepository musicEmojiVoteRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;
    private final EmojiRepository emojiRepository;

    @Override
    public VoteDto addEmojiVote(AddVoteDto dto) {
        validateAddVote(dto);

        MusicEmojiVote vote = generateEntity(dto);
        musicEmojiVoteRepository.save(vote);

        List<MusicEmojiVote> votes = musicEmojiVoteRepository.findAllByUserIdAndMusicId(dto.userId(), dto.musicId());
        return voteConverter.toDto(votes);
    }

    private void validateAddVote(AddVoteDto dto) {
        int voteCount = musicEmojiVoteRepository.countByUserIdAndMusicId(dto.userId(), dto.musicId());
        if(voteCount >= MAX_VOTE_COUNT) throw new ExceededMaxVoteException(dto.userId(), dto.musicId());

        List<MusicEmojiVote> existingVotes = musicEmojiVoteRepository.findAllByUserIdAndMusicId(dto.userId(), dto.musicId());
        List<String> existingEmojiIds = existingVotes.stream().map(MusicEmojiVote::getEmoji).map(Emoji::getId).toList();
        boolean isAlreadyVoted = existingEmojiIds.contains(dto.emojiId());
        if(isAlreadyVoted) throw new DuplicateVotedException(dto.userId(), dto.musicId(), dto.emojiId());
    }

    private MusicEmojiVote generateEntity(AddVoteDto dto) {
        String voteId = generateId();
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new UserNotFoundException(dto.userId()));
        Music music = musicRepository.findById(dto.musicId()).orElseThrow(() -> new MusicNotFoundException(dto.musicId()));
        Emoji emoji = emojiRepository.findById(dto.emojiId()).orElseThrow(() -> new EmojiNotFoundException(dto.emojiId()));
        if(!emoji.isCanUse()) throw new InvalidVoteEmojiException(dto.emojiId());

        return new MusicEmojiVote(voteId, user, music, emoji);
    }

    private String generateId() {
        return "mz2mo:vote:" + UUID.randomUUID();
    }
}
