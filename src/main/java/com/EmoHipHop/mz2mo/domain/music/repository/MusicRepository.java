package com.EmoHipHop.mz2mo.domain.music.repository;

import com.EmoHipHop.mz2mo.domain.music.data.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
    boolean existsBySpotifyMusicUri(String spotifyMusicUri);
}
