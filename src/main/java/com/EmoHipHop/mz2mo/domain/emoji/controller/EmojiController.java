package com.EmoHipHop.mz2mo.domain.emoji.controller;

import com.EmoHipHop.mz2mo.domain.emoji.data.dto.AddEmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.dto.EmojiDto;
import com.EmoHipHop.mz2mo.domain.emoji.data.request.CreateEmojiRequest;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiListResponse;
import com.EmoHipHop.mz2mo.domain.emoji.data.response.EmojiResponse;
import com.EmoHipHop.mz2mo.domain.emoji.service.EmojiService;
import com.EmoHipHop.mz2mo.domain.emoji.util.EmojiConverter;
import com.EmoHipHop.mz2mo.global.common.data.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/emojis")
public class EmojiController {
    private final EmojiConverter emojiConverter;
    private final EmojiService emojiService;

    @GetMapping
    public ResponseEntity<Response<EmojiListResponse>> getAllEmojis(
            @RequestParam boolean searchOnlyCanUse
    ) {
        List<EmojiDto> emojis = emojiService.getAllEmojis(searchOnlyCanUse);
        EmojiListResponse emojiListResponse = emojiConverter.toResponse(emojis);
        return ResponseEntity.ok(new Response<>("이모지 목록 조회에 성공하였습니다!", emojiListResponse));
    }

    @PostMapping
    public ResponseEntity<Response<EmojiResponse>> addEmoji(@RequestBody CreateEmojiRequest request) {
        AddEmojiDto addDto = emojiConverter.toDto(request);
        EmojiDto dto = emojiService.addEmoji(addDto);
        EmojiResponse response = emojiConverter.toResponse(dto);
        return ResponseEntity.ok(new Response<>("이모지 추가에 성공하였습니다!", response));
    }
}
