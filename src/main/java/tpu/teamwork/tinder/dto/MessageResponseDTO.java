package tpu.teamwork.tinder.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record MessageResponseDTO(
        UUID id,
        UUID fromUserId,
        UUID toUserId,
        String content,
        ZonedDateTime createdAt
) {}