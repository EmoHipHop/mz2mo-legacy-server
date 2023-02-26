package com.EmoHipHop.mz2mo.domain.vote.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    private String id;
    private String userId;
    private String musicId;
    @ElementCollection
    private List<String> currentVoteEmojiCodes;
}
