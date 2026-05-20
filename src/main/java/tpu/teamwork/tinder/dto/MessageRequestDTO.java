package tpu.teamwork.tinder.dto;

import java.util.UUID;

public record MessageRequestDTO(
        UUID toUserId,
        String content
) {}