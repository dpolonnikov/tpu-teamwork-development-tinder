package tpu.teamwork.tinder.config.kafka;

public interface KafkaProducer {
    <T> void produce(String topic, T t);
}
