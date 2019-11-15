package nn.dgordeev.kafka.api.service;

import nn.dgordeev.kafka.api.producer.DefaultKafkaProducerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class SyncKafkaProducerService implements KafkaProducerService {
    private KafkaProducer<String, String> producer;

    public SyncKafkaProducerService() {
        this.producer = DefaultKafkaProducerFactory.getProducer();
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            if (recordMetadata != null) {
                System.out.println("Sent to the topic: " + recordMetadata.topic());
                System.out.println("With offset: " + recordMetadata.offset());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
