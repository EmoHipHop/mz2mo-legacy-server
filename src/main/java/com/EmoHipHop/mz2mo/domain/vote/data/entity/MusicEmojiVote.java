package com.EmoHipHop.mz2mo.domain.vote.data.entity;

import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import com.EmoHipHop.mz2mo.global.music.data.entity.Music;
import com.EmoHipHop.mz2mo.global.user.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicEmojiVote {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "music_id")
    private Music music;
    @OneToOne
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;
}
