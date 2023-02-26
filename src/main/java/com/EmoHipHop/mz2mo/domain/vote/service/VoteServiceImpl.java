package com.EmoHipHop.mz2mo.domain.vote.service;

import com.EmoHipHop.mz2mo.domain.emoji.repository.EmojiRepository;
import com.EmoHipHop.mz2mo.domain.music.repository.MusicRepository;
import com.EmoHipHop.mz2mo.domain.user.repository.UserRepository;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.RemoveVoteDto;
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
import com.EmoHipHop.mz2mo.global.vote.data.event.VoteUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final ApplicationEventPublisher eventPublisher;

    @Override @Transactional
    public VoteDto addEmojiVote(AddVoteDto dto) {
        validateAddVote(dto);

        MusicEmojiVote vote = generateEntity(dto);
        musicEmojiVoteRepository.save(vote);

        VoteUpdateEvent event = new VoteUpdateEvent(dto.userId(), dto.musicId());
        eventPublisher.publishEvent(event);

        List<MusicEmojiVote> votes = musicEmojiVoteRepository.findAllByUserIdAndMusicId(dto.userId(), dto.musicId());
        return voteConverter.toDto(votes, dto.userId(), dto.musicId());
    }

    @Override @Transactional
    public VoteDto removeEmojiVote(RemoveVoteDto dto) {
        validateRemoveVote(dto);

        musicEmojiVoteRepository.deleteByUserIdAndMusicIdAndEmojiId(dto.userId(), dto.musicId(), dto.emojiId());

        List<MusicEmojiVote> votes = musicEmojiVoteRepository.findAllByUserIdAndMusicId(dto.userId(), dto.musicId());
        return voteConverter.toDto(votes, dto.userId(), dto.musicId());
    }

    private void validateRemoveVote(RemoveVoteDto dto) {
        boolean isUserExists = userRepository.existsById(dto.userId());
        boolean isMusicExists = musicRepository.existsById(dto.musicId());
        boolean isEmojiExists = emojiRepository.existsById(dto.emojiId());

        if (!isUserExists) throw new UserNotFoundException(dto.userId());
        if (!isMusicExists) throw new MusicNotFoundException(dto.musicId());
        if (!isEmojiExists) throw new EmojiNotFoundException(dto.emojiId());
    }

    private void validateAddVote(AddVoteDto dto) {
        boolean isUserExists = userRepository.existsById(dto.userId());
        boolean isMusicExists = musicRepository.existsById(dto.musicId());
        boolean isEmojiExists = emojiRepository.existsById(dto.emojiId());

        if (!isUserExists) throw new UserNotFoundException(dto.userId());
        if (!isMusicExists) throw new MusicNotFoundException(dto.musicId());
        if (!isEmojiExists) throw new EmojiNotFoundException(dto.emojiId());

        int voteCount = musicEmojiVoteRepository.countByUserIdAndMusicId(dto.userId(), dto.musicId());
        if(voteCount >= MAX_VOTE_COUNT) throw new ExceededMaxVoteException(dto.userId(), dto.musicId());

        List<MusicEmojiVote> existingVotes = musicEmojiVoteRepository.findAllByUserIdAndMusicId(dto.userId(), dto.musicId());
        List<String> existingEmojiIds = existingVotes.stream().map(MusicEmojiVote::getEmoji).map(Emoji::getId).toList();
        boolean isAlreadyVoted = existingEmojiIds.contains(dto.emojiId());
        if(isAlreadyVoted) throw new DuplicateVotedException(dto.userId(), dto.musicId(), dto.emojiId());
    }

    private MusicEmojiVote generateEntity(AddVoteDto dto) {
        String voteId = generateId();
        User user = userRepository.getReferenceById(dto.userId());
        Music music = musicRepository.getReferenceById(dto.musicId());
        Emoji emoji = emojiRepository.getReferenceById(dto.emojiId());
        if(!emoji.isCanUse()) throw new InvalidVoteEmojiException(dto.emojiId());

        return new MusicEmojiVote(voteId, user, music, emoji);
    }

    private String generateId() {
        return "mz2mo:vote:" + UUID.randomUUID();
    }
}
