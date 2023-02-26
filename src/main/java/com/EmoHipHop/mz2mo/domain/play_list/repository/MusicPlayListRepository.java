package com.EmoHipHop.mz2mo.domain.play_list.repository;

import com.EmoHipHop.mz2mo.domain.play_list.data.entity.MusicPlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicPlayListRepository extends JpaRepository<MusicPlayList, String> {

    void deleteByMusicId(String musicId);
}
