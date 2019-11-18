package nn.dgordeev.kafka.api.producer.service.entity;

import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.common.utils.KafkaUtils;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AsyncEntityKafkaProducerService implements KafkaProducerService<String, KafkaSerializable> {
    private final KafkaProducer<String, KafkaSerializable> producer;

    public AsyncEntityKafkaProducerService(KafkaProducer<String, KafkaSerializable> producer) {
        this.producer = producer;
    }

    @Override
    public void send(ProducerRecord<String, KafkaSerializable> record) {
        producer.send(record, KafkaUtils::asyncKafkaCallback);
    }
}
