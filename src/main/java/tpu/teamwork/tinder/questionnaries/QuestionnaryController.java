package tpu.teamwork.tinder.questionnaries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/questionnaries")
@RequiredArgsConstructor
public class QuestionnaryController {
    private final QuestionnaryService questionnaryService;

    @GetMapping
    public ResponseEntity<Page<QuestionnaryDTO>> getFeed(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return ResponseEntity.ok(questionnaryService.getFeed(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaryDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(questionnaryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<QuestionnaryDTO> create(
            // @RequestHeader("X-User-Id") UUID userId,
            @RequestBody QuestionnaryDTO questionnaryDTO) {
        QuestionnaryDTO created = questionnaryService.create(null, questionnaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionnaryDTO> update(
            @PathVariable UUID id,
            @RequestBody QuestionnaryDTO questionnaryDTO) {
        return ResponseEntity.ok(questionnaryService.update(id, questionnaryDTO));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<QuestionnaryDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam Boolean active) {
        return ResponseEntity.ok(questionnaryService.updateStatus(id, active));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        questionnaryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
