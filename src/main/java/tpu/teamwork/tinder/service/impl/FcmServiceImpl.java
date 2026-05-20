package tpu.teamwork.tinder.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.service.FcmService;

@Service
@Slf4j
public class FcmServiceImpl implements FcmService {

    @Override
    public void sendPushNotification(String targetToken, String title, String body) {
        try {
            // Создаем объект уведомления
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            // Привязываем уведомление к конкретному токену устройства
            Message message = Message.builder()
                    .setToken(targetToken)
                    .setNotification(notification)
                    .build();

            // Отправляем через Firebase SDK
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Push-уведомление успешно отправлено. ID ответа: {}", response);

        } catch (Exception e) {
            log.error("Не удалось отправить push-уведомление на токен {}: {}", targetToken, e.getMessage());
        }
    }
}