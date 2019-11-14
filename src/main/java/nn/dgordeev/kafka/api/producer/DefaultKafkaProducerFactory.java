package nn.dgordeev.kafka.api.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DefaultKafkaProducerFactory {
    private Properties producerProperties;
    private KafkaProducer<String, String> kafkaProducer;
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";

    public DefaultKafkaProducerFactory() {
        this.producerProperties = new Properties();
        initProps();
        kafkaProducer = new KafkaProducer<>(producerProperties);
    }

    private void initProps() {
        producerProperties.put(BOOTSTRAP_SERVERS, "localhost:9092");
        producerProperties.put(KEY_SERIALIZER, StringSerializer.class);
        producerProperties.put(VALUE_SERIALIZER, StringSerializer.class);
    }

    public KafkaProducer<String, String> getProducer() {
        return kafkaProducer;
    }

}
