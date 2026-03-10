package tpu.teamwork.tinder.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record QuestionnaryDTO(
    UUID id,
    String description,
    Integer age,
    Character gender,
    Integer course,
    String faculty,
    UUID dormitory,
    UUID image,
    ZonedDateTime createdAt
) {}