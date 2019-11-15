package nn.dgordeev.kafka.api.producer.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DefaultKafkaProducerFactory {
    private static KafkaProducer<String, String> INSTANCE;
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";

    private static Properties producerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, "localhost:9092");
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, StringSerializer.class);
        return properties;
    }

    public static KafkaProducer<String, String> getProducer() {
        if (INSTANCE == null) {
            INSTANCE = new KafkaProducer<>(producerProps());
        }
        return INSTANCE;
    }

}
