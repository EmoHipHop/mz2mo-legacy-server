package com.EmoHipHop.mz2mo.domain.music.controller;

import com.EmoHipHop.mz2mo.domain.music.data.dto.CreateMusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.dto.MusicDto;
import com.EmoHipHop.mz2mo.domain.music.data.request.CreateMusicRequest;
import com.EmoHipHop.mz2mo.domain.music.data.response.CreateMusicResponse;
import com.EmoHipHop.mz2mo.domain.music.service.MusicService;
import com.EmoHipHop.mz2mo.domain.music.util.MusicConverter;
import com.EmoHipHop.mz2mo.global.common.data.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/musics")
public class MusicController {
    private final MusicConverter musicConverter;
    private final MusicService musicService;

    @PostMapping
    public ResponseEntity<Response<CreateMusicResponse>>
    createMusic(@RequestBody CreateMusicRequest request) {
        CreateMusicDto createDto = musicConverter.toDto(request);
        MusicDto dto = musicService.createMusic(createDto);
        CreateMusicResponse response = musicConverter.toResponse(dto);
        return ResponseEntity.ok(new Response<>("음악 생성에 성공하였습니다!", response));
    }

}
