package tpu.teamwork.tinder.questionnaries;

import org.springframework.stereotype.Component;
import tpu.teamwork.tinder.entity.Questionnary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionnaryMapper {

    @Mapping(target = "dormitoryNumber", ignore = true) // Нужно логику получения номера из UUID
    @Mapping(target = "imageUrl", ignore = true)      // Нужно логику формирования ссылки
    QuestionnaryDTO toDto(Questionnary questionnary);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dormitory", ignore = true) // UUID нельзя просто так получить из Integer
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Questionnary toEntity(QuestionnaryDTO dto);
}