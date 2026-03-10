package tpu.teamwork.tinder.service.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tpu.teamwork.tinder.dto.KafkaEmailDTO;
import tpu.teamwork.tinder.utils.enums.MessageMode;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailListener {

    private final MailService mailService;

    @KafkaListener(
            topics = "email_message", groupId = "some"
    )
    void listen(
            KafkaEmailDTO kafkaMailMessage
    ) {
        log.info("email message: {} ", kafkaMailMessage);
        mailService.send(kafkaMailMessage, MessageMode.EMAIL_VERIFICATION);
    }
}