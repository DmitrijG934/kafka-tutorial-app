package nn.dgordeev.kafka.api.producer;

import nn.dgordeev.kafka.api.common.model.Customer;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.producer.factory.DefaultKafkaProducerFactory;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import nn.dgordeev.kafka.api.producer.service.entity.AsyncEntityKafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.UUID;

public class ProducerApplication {
    private static final String TOPIC_NAME = "test";

    public static void main(String[] args) {
        KafkaProducerService<String, KafkaSerializable> producerService =
                new AsyncEntityKafkaProducerService(DefaultKafkaProducerFactory.getCustomEntityProducer(KafkaProducerType.CUSTOMER));
        Customer customerToTopic = Customer.builder()
                .id(UUID.randomUUID())
                .name("jack wilson")
                .build();

        ProducerRecord<String, KafkaSerializable> producerRecord = new ProducerRecord<>(TOPIC_NAME, customerToTopic);
        producerService.send(producerRecord);
    }
}