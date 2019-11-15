package nn.dgordeev.kafka.api.producer.service;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface KafkaProducerService {
    void send(ProducerRecord<String, String> record);
}