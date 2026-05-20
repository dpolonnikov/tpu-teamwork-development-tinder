package tpu.teamwork.tinder.service;

import tpu.teamwork.tinder.dto.MessageRequestDTO;
import tpu.teamwork.tinder.dto.MessageResponseDTO;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    /**
     * Сохранить сообщение в БД с предварительной проверкой на взаимный мэтч.
     */
    MessageResponseDTO saveMessage(MessageRequestDTO requestDTO);

    /**
     * Получить историю чата между текущим пользователем и собеседником.
     */
    List<MessageResponseDTO> getChatHistory(UUID companionId);
}