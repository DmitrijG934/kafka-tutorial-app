package nn.dgordeev.kafka.api.service;

import nn.dgordeev.kafka.api.producer.DefaultKafkaProducerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AsyncKafkaProducerService implements KafkaProducerService {
    private KafkaProducer<String, String> producer;

    public AsyncKafkaProducerService() {
        this.producer = DefaultKafkaProducerFactory.getProducer();
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        producer.send(record, (metadata, exception) -> {
            if (metadata != null) {
                System.out.println("Sent to the topic: " + metadata.topic());
                System.out.println("With offset: " + metadata.offset());
            }
        });
    }
}
