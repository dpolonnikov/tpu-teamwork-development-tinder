package tpu.teamwork.tinder.dto;

public record KafkaEmailDTO(
        String email,
        String message,
        String dataToSend
) {
}
