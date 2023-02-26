package com.EmoHipHop.mz2mo.domain.play_list.repository;

import com.EmoHipHop.mz2mo.domain.play_list.data.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayList, String> {
    Optional<PlayList> findByMusicsMusicId(String musicId);
    Optional<PlayList> findFirstByEmojiId(String emojiId);
}