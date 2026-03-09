package tpu.teamwork.tinder.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        String password
) {
}
