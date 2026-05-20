package tpu.teamwork.tinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpu.teamwork.tinder.service.FcmService;

@RestController
@RequestMapping("/api/v1/test/")
@RequiredArgsConstructor
public class UserController {

    // Я ПРОВЕРЯЛ ПОЛУЧЕНИЕ ОТВЕТА FIREBASE
    // МОЖНО СПОКОЙНО УДАЛЯТЬ ЕСЛИ НАДО

    private final FcmService fcmService;

    @PostMapping("/push")
    public ResponseEntity<String> testPush() {
        // Подставляем любой длинный фейковый токен в стиле Firebase
        String fakeToken = "fX8...fake_token_structure...R4b";

        fcmService.sendPushNotification(fakeToken, "Тест Бэкэнда", "Привет, это проверка FCM SDK!");
        return ResponseEntity.ok("Запрос отправлен в Firebase, проверь логи приложения!");
    }
}