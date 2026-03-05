package tpu.teamwork.tinder.questionnaries;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import tpu.teamwork.tinder.entity.Questionnary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
    public QuestionnaryDTO findById(UUID id) {
        Questionnary questionnary = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена: " + id));

        return questionnaryMapper.toDto(questionnary);
    }

    @Override
    public Page<QuestionnaryDTO> getFeed(Pageable pageable) {
        Page<Questionnary> questionnaires = questionnaryRepository.findAll(pageable);

        // TODO: Надо учесть isActive = true

        return questionnaires.map(questionnaryMapper::toDto);
    }

    @Override
    @Transactional
    public QuestionnaryDTO create(UUID userId, QuestionnaryDTO questionnaryDTO) {
        Questionnary questionnary = questionnaryMapper.toEntity(questionnaryDTO);
        questionnary.setIsActive(true);
        Questionnary saved = questionnaryRepository.save(questionnary);

        return questionnaryMapper.toDto(saved);
    }

    @Override
    public QuestionnaryDTO update(UUID id, QuestionnaryDTO questionnaryDTO) {
        Questionnary existing = questionnaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Анкета не найдена: " + id));

        existing.setDescription(questionnaryDTO.description());
        existing.setAge(questionnaryDTO.age());
        if (questionnaryDTO.gender() != null && !questionnaryDTO.gender().isEmpty()) {
            existing.setGender(questionnaryDTO.gender().charAt(0));
        }
        existing.setCourse(questionnaryDTO.course());
        existing.setFaculty(questionnaryDTO.faculty());

        // TODO: обновить dormitory и image, если надо

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

        // Мягкое удаление - просто делаем неактивной
        questionnary.setIsActive(false);
        questionnaryRepository.save(questionnary);
    }
}