package tpu.teamwork.tinder.dto;

import java.util.UUID;

public record MatchResponseDTO(
        UUID userId,
        String username,
        UUID questionnaryId,
        String description,
        Integer age,
        UUID imageId
) {}