package tpu.teamwork.tinder.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tpu.teamwork.tinder.dto.QuestionnaryDTO;

import java.util.UUID;

public interface QuestionnaryService {
    Page<QuestionnaryDTO> getFeed(Pageable pageable);
    Page<QuestionnaryDTO> getFeedFilter(UUID userId, QuestionnaryDTO filterDTO, Pageable pageable);
    QuestionnaryDTO findById(UUID id);
    QuestionnaryDTO create(UUID id, QuestionnaryDTO questionnaryDTO);
    QuestionnaryDTO update(UUID id, QuestionnaryDTO questionnaryDTO);
    QuestionnaryDTO updateStatus(UUID id, Boolean isActive);
    void delete(UUID id);
}