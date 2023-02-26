package com.EmoHipHop.mz2mo.domain.play_list.data.entity;

import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {
    @Id
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    @OneToOne @JoinColumn(referencedColumnName = "id")
    private Emoji emoji;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "playlistId")
    private List<MusicPlayList> musics;

    public void addMusicPlayList(MusicPlayList musicPlayList) {
        musics.add(musicPlayList);
    }

    public void removeMusic(String musicId) {
        musics.removeIf(musicPlayList -> musicPlayList.getMusicId().equals(musicId));
    }
}
