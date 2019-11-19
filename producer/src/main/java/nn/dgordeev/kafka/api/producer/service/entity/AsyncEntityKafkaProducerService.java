package nn.dgordeev.kafka.api.producer.service.entity;

import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.common.model.report.ApiReport;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class AsyncEntityKafkaProducerService implements KafkaProducerService<String, KafkaSerializable> {
    private final KafkaProducer<String, KafkaSerializable> producer;

    public AsyncEntityKafkaProducerService(KafkaProducer<String, KafkaSerializable> producer) {
        this.producer = producer;
    }

    @Override
    public void send(ProducerRecord<String, KafkaSerializable> record) {
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    ApiReport report = ApiReport.builder()
                            .error(exception)
                            .message("Exception happened during sending from " + this.getClass() + " to topic with name: " + record.topic())
                            .build();
                    System.out.println(String.format("Error happened: %s.", report));
                }
                if (metadata != null) {
                    System.out.println(String.format("Successfully sent message to kafka topic with name %s.", record.topic()));
                    System.out.println(String.format("%s", metadata));
                }
            }
        });
    }
}
