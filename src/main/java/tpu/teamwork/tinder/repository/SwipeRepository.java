package tpu.teamwork.tinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpu.teamwork.tinder.dto.MatchResponseDTO;
import tpu.teamwork.tinder.entity.Swipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, UUID> {

    Optional<Swipe> findByFromUserIdAndToUserId(UUID fromUserId, UUID toUserId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Swipe s " +
            "WHERE s.fromUserId = :toUserId AND s.toUserId = :fromUserId AND s.isLike = true")
    boolean isMutualLike(@Param("fromUserId") UUID fromUserId, @Param("toUserId") UUID toUserId);

    // Новый запрос для получения списка мэтчей с деталями анкеты
    @Query("SELECT new tpu.teamwork.tinder.dto.MatchResponseDTO(u.id, u.username, q.id, q.description, q.age, q.image) " +
            "FROM Swipe s1 " +
            "JOIN Swipe s2 ON s1.fromUserId = s2.toUserId AND s1.toUserId = s2.fromUserId " +
            "JOIN User u ON u.id = s1.toUserId " +
            "LEFT JOIN Questionnary q ON u.questionnaryId = q.id " +
            "WHERE s1.fromUserId = :currentUserId AND s1.isLike = true AND s2.isLike = true")
    List<MatchResponseDTO> findMatchesByUserId(@Param("currentUserId") UUID currentUserId);
}