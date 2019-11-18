package nn.dgordeev.kafka.api.producer.service;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface KafkaProducerService<K, V> {
    void send(ProducerRecord<K, V> record);
}
