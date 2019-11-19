package nn.dgordeev.kafka.api.producer.service.string;

import lombok.extern.slf4j.Slf4j;
import nn.dgordeev.kafka.api.common.model.report.ApiReport;
import nn.dgordeev.kafka.api.producer.factory.DefaultKafkaProducerFactory;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class AsyncKafkaProducerService implements KafkaProducerService<String, String> {
    private KafkaProducer<String, String> producer;

    public AsyncKafkaProducerService() {
        this.producer = DefaultKafkaProducerFactory.getStringProducer();
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        producer.send(record, ((metadata, exception) -> {
            if (exception != null) {
                ApiReport report = ApiReport.builder()
                        .error(exception)
                        .message("Exception happened during sending from " + this.getClass() + " to topic with name: " + record.topic())
                        .build();
                log.error("Error: {}", report);
            }
            if (metadata != null) {
                log.info("Successfully sent message to kafka topic with name {}.", record.topic());
                log.debug("{}", record);
            }
        }));

    }
}
