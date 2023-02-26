package com.EmoHipHop.mz2mo.domain.play_list.presenter.listener;

import com.EmoHipHop.mz2mo.domain.play_list.service.PlayListService;
import com.EmoHipHop.mz2mo.global.vote.data.event.VoteUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteUpdateEventListener {
    private final PlayListService playListService;

    @EventListener(VoteUpdateEvent.class)
    public void onVoteUpdate(VoteUpdateEvent event) {
        System.out.println("이벤트 발생");
        String musicId = event.getMusicId();
        System.out.printf("musicId: %s", musicId);
        playListService.updatePlayList(musicId);
    }
}
