package nn.dgordeev.kafka.api;

import nn.dgordeev.kafka.api.service.AsyncKafkaProducerService;
import nn.dgordeev.kafka.api.service.KafkaProducerService;
import nn.dgordeev.kafka.api.service.SyncKafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Application {
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
