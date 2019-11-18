package nn.dgordeev.kafka.api.producer.service.string;

import nn.dgordeev.kafka.api.common.utils.KafkaUtils;
import nn.dgordeev.kafka.api.producer.factory.DefaultKafkaProducerFactory;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AsyncKafkaProducerService implements KafkaProducerService<String, String> {
    private KafkaProducer<String, String> producer;

    public AsyncKafkaProducerService() {
        this.producer = DefaultKafkaProducerFactory.getStringProducer();
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        producer.send(record, KafkaUtils::processKafkaMetadata);

    }
}
