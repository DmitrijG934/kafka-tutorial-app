package nn.dgordeev.kafka.api;

import nn.dgordeev.kafka.api.producer.DefaultKafkaProducerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class Application {
    private static final String TOPIC_NAME = "test";

    public static void main(String[] args) {
        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory();
        KafkaProducer<String, String> producer = producerFactory.getProducer();

        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(TOPIC_NAME, "foo", "bar");

        for (int i = 0; i < 5; i++) {
            sendProducerRecord(producerRecord, producer);
        }
    }

    private static void sendProducerRecord(ProducerRecord<String, String> record, KafkaProducer<String, String> producer) {
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
