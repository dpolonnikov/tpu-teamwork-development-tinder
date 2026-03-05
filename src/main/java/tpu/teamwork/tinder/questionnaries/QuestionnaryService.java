package tpu.teamwork.tinder.questionnaries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface QuestionnaryService {
    QuestionnaryDTO findById(UUID id);
    Page<QuestionnaryDTO> getFeed(Pageable pageable);
    QuestionnaryDTO create(UUID id, QuestionnaryDTO questionnaryDTO);
    QuestionnaryDTO update(UUID id, QuestionnaryDTO questionnaryDTO);
    QuestionnaryDTO updateStatus(UUID id, Boolean isActive);
    void delete(UUID id);
}