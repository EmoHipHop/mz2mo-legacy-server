package com.EmoHipHop.mz2mo.domain.emoji.repository;

import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmojiRepository extends JpaRepository<Emoji, String> {
    List<Emoji> findAllByCanUse(boolean canUse);
    Optional<Emoji> findByCode(String code);
}
