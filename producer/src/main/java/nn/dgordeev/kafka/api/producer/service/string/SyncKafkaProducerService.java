package nn.dgordeev.kafka.api.producer.service.string;

import nn.dgordeev.kafka.api.common.model.report.ApiReport;
import nn.dgordeev.kafka.api.common.utils.KafkaUtils;
import nn.dgordeev.kafka.api.producer.factory.DefaultKafkaProducerFactory;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

public class SyncKafkaProducerService implements KafkaProducerService<String, String> {
    private KafkaProducer<String, String> producer;

    public SyncKafkaProducerService() {
        this.producer = DefaultKafkaProducerFactory.getStringProducer();
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        try {
            producer.send(record, KafkaUtils::asyncKafkaCallback).get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(ApiReport.builder()
                    .message("Error happened during sync sending to Kafka topic.")
                    .error(e).build());
        }
    }
}
