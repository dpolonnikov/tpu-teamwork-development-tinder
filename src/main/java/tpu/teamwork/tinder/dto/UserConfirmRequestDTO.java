package tpu.teamwork.tinder.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record UserConfirmRequestDTO(
        String username,
        String email,
        String password

) {
}
