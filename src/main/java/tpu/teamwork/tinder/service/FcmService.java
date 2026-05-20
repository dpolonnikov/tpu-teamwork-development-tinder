package tpu.teamwork.tinder.service;

public interface FcmService {
    /**
     * Отправить push-уведомление на конкретное устройство.
     * @param targetToken - токен устройства (из сущности DeviceToken)
     * @param title - заголовок (например, "Новый мэтч!")
     * @param body - текст (например, "У вас взаимная симпатия с Аней")
     */
    void sendPushNotification(String targetToken, String title, String body);
}