package com.EmoHipHop.mz2mo.domain.play_list.service;

import com.EmoHipHop.mz2mo.domain.music.repository.MusicRepository;
import com.EmoHipHop.mz2mo.domain.play_list.data.entity.MusicPlayList;
import com.EmoHipHop.mz2mo.domain.play_list.data.entity.PlayList;
import com.EmoHipHop.mz2mo.domain.play_list.repository.MusicPlayListRepository;
import com.EmoHipHop.mz2mo.domain.play_list.repository.PlayListRepository;
import com.EmoHipHop.mz2mo.domain.vote.data.entity.MusicEmojiVote;
import com.EmoHipHop.mz2mo.domain.vote.repository.MusicEmojiVoteRepository;
import com.EmoHipHop.mz2mo.global.emoji.data.entity.Emoji;
import com.EmoHipHop.mz2mo.global.music.data.entity.Music;
import com.EmoHipHop.mz2mo.global.music.exception.MusicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {
    private static final int MIN_VOTE_COUNT_FOR_PLAYLIST = 10;

    private final PlayListRepository playListRepository;
    private final MusicEmojiVoteRepository voteRepository;
    private final MusicRepository musicRepository;
    private final MusicPlayListRepository musicPlayListRepository;

    @Override @Transactional
    public void updatePlayList(String musicId) {
        //LOGGING ALL VARIVBLES WITH PRINTF AND JSON STYLE INNER FIELD!!
        List<MusicEmojiVote> votes =  voteRepository.findAllByMusicId(musicId);
        System.out.printf("votes: %s", votes.stream().map(MusicEmojiVote::getId).toArray());
        Optional<Map.Entry<Emoji, Integer>> voteResultsOptional = getVoteData(votes);
        System.out.printf("voteResultsOptional: %s", voteResultsOptional);
        if(voteResultsOptional.isEmpty()) return;
        System.out.println("true");

        Map.Entry<Emoji, Integer> voteResults = voteResultsOptional.get();
        Music music = musicRepository.findById(musicId).orElseThrow(() ->new MusicNotFoundException(musicId));

        Emoji emoji = voteResults.getKey();
        System.out.println("emoji: " + emoji.getId());
        int voteCount = voteResults.getValue();
        System.out.println("voteCount: " + voteCount);

        if(voteCount >= MIN_VOTE_COUNT_FOR_PLAYLIST) {
            LocalDateTime now = LocalDateTime.now();
            Optional<PlayList> existPlayListOptional = playListRepository.findByMusicsMusicId(musicId);
            System.out.printf("existPlayListOptional: %s", existPlayListOptional.toString());
            PlayList newPlayList = playListRepository.findFirstByEmojiId(emoji.getId())
                    .orElseGet(() -> playListRepository.save(new PlayList(generateId(), "", now, now, emoji, List.of())));
            System.out.printf("newPlayList: %s", newPlayList);
            //기존 플레이리스트에 음악이 존재하는경우 삭제한다.
            existPlayListOptional.ifPresent(playList -> deleteMusic(playList, musicId));
            //새 플레이리스트에 음악을 추가한다.
            boolean isExist = newPlayList.getMusics().stream()
                    .map(MusicPlayList::getMusicId)
                    .anyMatch(music.getId()::equals);
            if(isExist) return;
            MusicPlayList musicPlayList = new MusicPlayList(null, newPlayList.getId(), music.getId());
            musicPlayListRepository.save(musicPlayList);
            newPlayList.addMusicPlayList(musicPlayList);
            playListRepository.save(newPlayList);
        }
        else {
            //최소 투표수를 넘지 못했다면 기존 플레이리스트에서 삭제한다.
            Optional<PlayList> playListOptional = playListRepository.findByMusicsMusicId(musicId);
            playListOptional.ifPresent(playList -> deleteMusic(playList, musicId));
        }
    }

    private void deleteMusic(PlayList playList, String musicId) {
        playList.removeMusic(musicId);
        musicPlayListRepository.deleteByMusicId(musicId);
        playListRepository.save(playList);
    }

    private Optional<Map.Entry<Emoji, Integer>> getVoteData(List<MusicEmojiVote> votes) {
        return votes.stream()
                .map(MusicEmojiVote::getEmoji).collect(Collectors.groupingBy(Emoji::getId))
                .values()
                .stream()
                .map(list -> Map.entry(list.get(0), list.size()))
                .max(Comparator.comparingInt(Map.Entry::getValue));
    }

    private String generateId() {
        return "mz2mo:playlist:" + UUID.randomUUID();
    }
}
