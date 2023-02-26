package com.EmoHipHop.mz2mo.domain.vote.controller;

import com.EmoHipHop.mz2mo.domain.vote.data.dto.AddVoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.dto.VoteDto;
import com.EmoHipHop.mz2mo.domain.vote.data.request.AddVoteRequest;
import com.EmoHipHop.mz2mo.domain.vote.data.response.AddVoteResponse;
import com.EmoHipHop.mz2mo.domain.vote.service.VoteService;
import com.EmoHipHop.mz2mo.domain.vote.util.VoteConverter;
import com.EmoHipHop.mz2mo.global.common.data.response.Response;
import com.EmoHipHop.mz2mo.global.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/musics/{id}/emoji-votes")
public class MusicEmojiVoteController {
    private final VoteConverter voteConverter;
    private final VoteService voteService;
    private final LoginUserService loginUserService;

    @PostMapping
    public ResponseEntity<Response<AddVoteResponse>> addEmojiVote(
            @PathVariable("id") String musicId,
            @RequestBody AddVoteRequest request
    ) {
        String userId = loginUserService.getLoginUserId();
        AddVoteDto addDto = voteConverter.toDto(request, userId, musicId);
        VoteDto dto = voteService.addEmojiVote(addDto);
        AddVoteResponse response = voteConverter.toResponse(dto);
        return ResponseEntity.ok(new Response<>("투표 추가에 성공하였습니다!", response));
    }
}
