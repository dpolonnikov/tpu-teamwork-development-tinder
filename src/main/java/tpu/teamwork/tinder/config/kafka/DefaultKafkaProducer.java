package tpu.teamwork.tinder.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultKafkaProducer implements KafkaProducer{
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public <T> void produce(String topic, T t) {
        kafkaTemplate.send(topic, t).whenComplete((res, th)-> {
            log.info("Отправлено сообщение: " + res.getProducerRecord() + " в топик: " + res.getProducerRecord().topic());
        });
    }
}
