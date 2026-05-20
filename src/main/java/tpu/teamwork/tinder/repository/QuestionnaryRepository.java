package tpu.teamwork.tinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tpu.teamwork.tinder.entity.Questionnary;
import java.util.UUID;

public interface QuestionnaryRepository extends JpaRepository<Questionnary, UUID>,
        JpaSpecificationExecutor<Questionnary> {
}
