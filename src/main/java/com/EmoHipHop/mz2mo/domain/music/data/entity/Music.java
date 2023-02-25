package com.EmoHipHop.mz2mo.domain.music.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @Id
    private String id;
    private LocalDateTime createAt;
    private String spotifyMusicUri;
    private String spotifyArtistUri;
}
