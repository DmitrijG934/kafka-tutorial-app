package nn.dgordeev.kafka.api.producer;

import nn.dgordeev.kafka.api.producer.service.AsyncKafkaProducerService;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import nn.dgordeev.kafka.api.producer.service.SyncKafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerApplication {
    private static final String TOPIC_NAME = "test";

    public static void main(String[] args) {
        KafkaProducerService syncProducerService = new SyncKafkaProducerService();
        KafkaProducerService asyncProducerService = new AsyncKafkaProducerService();

        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(TOPIC_NAME, "foo", "bar");

        syncProducerService.send(producerRecord);
        asyncProducerService.send(producerRecord);
    }
}