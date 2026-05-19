package tpu.teamwork.tinder.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpu.teamwork.tinder.entity.Swipe;
import tpu.teamwork.tinder.entity.User;
import tpu.teamwork.tinder.repository.SwipeRepository;
import tpu.teamwork.tinder.service.SwipeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SwipeServiceImpl implements SwipeService {

    SwipeRepository swipeRepository;

    @Override
    @Transactional
    public boolean saveSwipe(UUID toUserId, Boolean isLike) {
        // 1. Получаем текущего авторизованного пользователя из Spring Security
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID fromUserId = currentUser.getId();

        // 2. Защита от свайпа самого себя
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Вы не можете оценивать самого себя");
        }

        // 3. Проверяем, существовал ли свайп ранее (если пользователь передумал или это повторный запрос)
        Swipe swipe = swipeRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
                .orElse(new Swipe());

        swipe.setFromUserId(fromUserId);
        swipe.setToUserId(toUserId);
        swipe.setIsLike(isLike);

        swipeRepository.save(swipe);

        // 4. Алгоритм поиска совпадений: если это лайк, проверяем взаимность
        if (Boolean.TRUE.equals(isLike)) {
            return swipeRepository.isMutualLike(fromUserId, toUserId);
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.List<tpu.teamwork.tinder.dto.MatchResponseDTO> getCurrentUserMatches() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return swipeRepository.findMatchesByUserId(currentUser.getId());
    }
}