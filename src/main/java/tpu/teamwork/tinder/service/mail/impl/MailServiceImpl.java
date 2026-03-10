package tpu.teamwork.tinder.service.mail.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.dto.KafkaEmailDTO;
import tpu.teamwork.tinder.service.mail.MailService;
import tpu.teamwork.tinder.utils.enums.MessageMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private String frontEndURL;

    @Override
    public void send(KafkaEmailDTO kafkaMailMessage, MessageMode mode) {
        var msg = new SimpleMailMessage();

        if (mode == MessageMode.EMAIL_VERIFICATION) {
            msg.setText(frontEndURL + "/verification?data=" + kafkaMailMessage.message());
        } else {
            msg.setText(kafkaMailMessage.message());
        }

        msg.setTo(kafkaMailMessage.email());
        msg.setFrom("d.polonnikoff@yandex.ru");

        try {
            mailSender.send(msg);
            log.info("Отправлено письмо, msg: {}, mode: {}", kafkaMailMessage, mode);
        } catch (Exception e) {
            log.error("Ошибка отправки письма : {}", e.getMessage());
        }


    }
}