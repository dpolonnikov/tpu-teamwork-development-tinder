package tpu.teamwork.tinder.service.mail;

import tpu.teamwork.tinder.dto.KafkaEmailDTO;
import tpu.teamwork.tinder.utils.enums.MessageMode;

public interface MailService {
    void send(KafkaEmailDTO kafkaMailMessage, MessageMode mode);
}