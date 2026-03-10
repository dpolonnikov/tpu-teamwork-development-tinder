package tpu.teamwork.tinder.service.impl;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import tpu.teamwork.tinder.dto.QuestionnaryDTO;
import tpu.teamwork.tinder.entity.Questionnary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.mapper.QuestionnaryMapper;
import tpu.teamwork.tinder.questionnarySpecs.QuestionnarySpecs;
import tpu.teamwork.tinder.repository.QuestionnaryRepository;
import tpu.teamwork.tinder.service.QuestionnaryService;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class QuestionnaryServiceImpl implements QuestionnaryService {

    QuestionnaryRepository questionnaryRepository;
    QuestionnaryMapper questionnaryMapper;

    @Override
    public Page<QuestionnaryDTO> getFeed(Pageable pageable) {
        return questionnaryRepository.findAll(pageable)
                .map(questionnaryMapper::toDto);
    }

    @Override
    public Page<QuestionnaryDTO> getFeedFilter(UUID userId, QuestionnaryDTO filter, Pageable pageable) {
        UUID userDormitory = null;
        Character userGender = null;

        // TODO: Насколько я понял UserId используется сейчас как id анкеты, нужно будет доработать
        // Тут UserId нужен для реализации поиска по параметрам пользователя (один пол, одно общежитие и исключение самого себя)

        if (userId != null) {
            var userQuestionnaire = questionnaryRepository.findById(userId).orElse(null);
            if (userQuestionnaire != null) {
                userDormitory = userQuestionnaire.getDormitory();
                userGender = userQuestionnaire.getGender();
            }
        }

        Specification<Questionnary> spec = Specification.where(QuestionnarySpecs.isActive())
                .and(QuestionnarySpecs.hasDormitory(userDormitory))
                .and(QuestionnarySpecs.hasGender(userGender))
                .and(QuestionnarySpecs.notSelf(userId));

        // В будущем сюда легко добавить:
        // if (filter.age() != null) spec = spec.and(...);

        return questionnaryRepository.findAll(spec, pageable)
                .map(questionnaryMapper::toDto);
    }

    @Override
    public QuestionnaryDTO findById(UUID id) {
        Questionnary questionnary = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена: " + id));

        return questionnaryMapper.toDto(questionnary);
    }

    @Override
    @Transactional
    public QuestionnaryDTO create(UUID userId, QuestionnaryDTO questionnaryDTO) {

        // TODO: Реализовать применение userId, чтобы связать сделать update для поля questionnaryId в сущности User

        Questionnary questionnary = questionnaryMapper.toEntity(questionnaryDTO);
        questionnary.setIsActive(true);
        Questionnary saved = questionnaryRepository.save(questionnary);

        return questionnaryMapper.toDto(saved);
    }

    @Override
    @Transactional
    public QuestionnaryDTO update(UUID id, QuestionnaryDTO dto) {
        Questionnary existing = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена"));

        questionnaryMapper.updateEntityFromDto(dto, existing);

        Questionnary saved = questionnaryRepository.save(existing);
        return questionnaryMapper.toDto(saved);
    }

    @Override
    @Transactional
    public QuestionnaryDTO updateStatus(UUID id, Boolean isActive) {
        Questionnary questionnary = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена: " + id));

        questionnary.setIsActive(isActive);
        Questionnary saved = questionnaryRepository.save(questionnary);
        return questionnaryMapper.toDto(saved);
    }

    @Override
    public void delete(UUID id) {
        Questionnary questionnary = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена: " + id));

        questionnaryRepository.delete(questionnary);
    }
}