package tpu.teamwork.tinder.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpu.teamwork.tinder.dto.MessageRequestDTO;
import tpu.teamwork.tinder.dto.MessageResponseDTO;
import tpu.teamwork.tinder.entity.Message;
import tpu.teamwork.tinder.entity.User;
import tpu.teamwork.tinder.repository.MessageRepository;
import tpu.teamwork.tinder.repository.SwipeRepository;
import tpu.teamwork.tinder.service.MessageService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;
    SwipeRepository swipeRepository;

    @Override
    @Transactional
    public MessageResponseDTO saveMessage(MessageRequestDTO requestDTO) {
        // 1. Получаем отправителя из контекста безопасности
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID fromUserId = currentUser.getId();
        UUID toUserId = requestDTO.toUserId();

        // 2. Бизнес-проверка: можно писать только если есть взаимный лайк (мэтч)
        boolean isMatch = swipeRepository.isMutualLike(fromUserId, toUserId);
        if (!isMatch) {
            throw new IllegalStateException("Вы не можете отправлять сообщения пользователю без взаимной симпатии!");
        }

        // 3. Создаем и сохраняем сообщение
        Message message = new Message();
        message.setFromUserId(fromUserId);
        message.setToUserId(toUserId);
        message.setContent(requestDTO.content());

        Message savedMessage = messageRepository.save(message);

        // 4. Возвращаем DTO для отправки через WebSocket брокер
        return new MessageResponseDTO(
                savedMessage.getId(),
                savedMessage.getFromUserId(),
                savedMessage.getToUserId(),
                savedMessage.getContent(),
                savedMessage.getCreatedAt()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponseDTO> getChatHistory(UUID companionId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID currentUserId = currentUser.getId();

        return messageRepository.findChatHistory(currentUserId, companionId).stream()
                .map(msg -> new MessageResponseDTO(
                        msg.getId(),
                        msg.getFromUserId(),
                        msg.getToUserId(),
                        msg.getContent(),
                        msg.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}