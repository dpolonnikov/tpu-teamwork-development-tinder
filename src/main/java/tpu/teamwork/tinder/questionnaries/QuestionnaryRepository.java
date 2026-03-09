package tpu.teamwork.tinder.questionnaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tpu.teamwork.tinder.entity.Questionnary;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface QuestionnaryRepository extends JpaRepository<Questionnary, UUID>,
        JpaSpecificationExecutor<Questionnary> {}
