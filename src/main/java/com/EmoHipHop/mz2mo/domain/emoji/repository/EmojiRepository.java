package com.EmoHipHop.mz2mo.domain.emoji.repository;

import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmojiRepository extends JpaRepository<Emoji, String> {
    List<Emoji> findAllByCanUse(boolean canUse);
}
