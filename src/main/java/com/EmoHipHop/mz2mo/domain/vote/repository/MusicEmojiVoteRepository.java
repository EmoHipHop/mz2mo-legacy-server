package com.EmoHipHop.mz2mo.domain.vote.repository;

import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicEmojiVoteRepository extends JpaRepository<MusicEmojiVote, String> {
    int countByUserIdAndMusicId(String userId, String musicId);
    List<MusicEmojiVote> findAllByUserIdAndMusicId(String userId, String musicId);
    void deleteByUserIdAndMusicIdAndEmojiId(String userId, String musicId, String emojiId);
}
