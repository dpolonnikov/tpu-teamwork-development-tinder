package tpu.teamwork.tinder.mapper;

import org.mapstruct.*;
import tpu.teamwork.tinder.dto.QuestionnaryDTO;
import tpu.teamwork.tinder.entity.Questionnary;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface QuestionnaryMapper {
    QuestionnaryDTO toDto(Questionnary questionnary);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Questionnary toEntity(QuestionnaryDTO dto);

    // Метод для обновления существующей сущности
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateEntityFromDto(QuestionnaryDTO dto, @MappingTarget Questionnary entity);
}