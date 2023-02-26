package com.EmoHipHop.mz2mo.domain.emoji.repository;

import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, String> {
}
