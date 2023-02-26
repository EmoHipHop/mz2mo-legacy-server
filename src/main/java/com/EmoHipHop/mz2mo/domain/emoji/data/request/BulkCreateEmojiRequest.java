package com.EmoHipHop.mz2mo.domain.emoji.data.request;

import java.util.List;

public record BulkCreateEmojiRequest(List<CreateEmojiRequest> list) {
}
