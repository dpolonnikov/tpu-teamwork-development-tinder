package tpu.teamwork.tinder.questionnaries;

import java.time.ZonedDateTime;
import java.util.UUID;

public record QuestionnaryDTO(
    UUID id,
    String description,
    Integer age,
    String gender,
    Integer course,
    String faculty,
    Integer dormitoryNumber,
    String imageUrl,
    ZonedDateTime createdAt
) {}