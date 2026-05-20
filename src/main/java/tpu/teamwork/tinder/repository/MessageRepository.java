package tpu.teamwork.tinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpu.teamwork.tinder.entity.Message;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    // Получить историю сообщений между двумя пользователями, отсортированную по времени
    @Query("SELECT m FROM Message m WHERE " +
            "(m.fromUserId = :user1 AND m.toUserId = :user2) OR " +
            "(m.fromUserId = :user2 AND m.toUserId = :user1) " +
            "ORDER BY m.createdAt ASC")
    List<Message> findChatHistory(@Param("user1") UUID user1, @Param("user2") UUID user2);
}