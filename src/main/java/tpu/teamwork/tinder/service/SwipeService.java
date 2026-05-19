package tpu.teamwork.tinder.service;

import java.util.UUID;

public interface SwipeService {
    /**
     * Сохранить свайп пользователя.
     * @param toUserId - кого свайпают
     * @param isLike - true (лайк), false (дизлайк)
     * @return true, если образовался мэтч (взаимный лайк)
     */
    boolean saveSwipe(UUID toUserId, Boolean isLike);
}