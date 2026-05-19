package tpu.teamwork.tinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpu.teamwork.tinder.entity.Swipe;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, UUID> {

    // Проверить, существует ли уже свайп между пользователями (чтобы не дублировать)
    Optional<Swipe> findByFromUserIdAndToUserId(UUID fromUserId, UUID toUserId);

    // Проверка на взаимность (тот самый мэтч «на лету» для Димы)
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Swipe s " +
            "WHERE s.fromUserId = :toUserId AND s.toUserId = :fromUserId AND s.isLike = true")
    boolean isMutualLike(@Param("fromUserId") UUID fromUserId, @Param("toUserId") UUID toUserId);
}