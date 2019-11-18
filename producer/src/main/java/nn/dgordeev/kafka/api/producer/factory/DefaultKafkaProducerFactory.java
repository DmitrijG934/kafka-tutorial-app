package nn.dgordeev.kafka.api.producer.factory;

import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.producer.serializer.CustomerSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DefaultKafkaProducerFactory {
    private static KafkaProducer<String, String> STRING_KAFKA_PRODUCER;
    private static KafkaProducer<String, KafkaSerializable> CUSTOM_ENTITY_PRODUCER;
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";

    private static Properties defaultProducerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, "localhost:9092");
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, StringSerializer.class);
        return properties;
    }

    private static Properties customerProducerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, "localhost:9092");
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, CustomerSerializer.class);
        return properties;
    }

    public static KafkaProducer<String, String> getStringProducer() {
        if (STRING_KAFKA_PRODUCER == null) {
            STRING_KAFKA_PRODUCER = new KafkaProducer<>(defaultProducerProps());
        }
        return STRING_KAFKA_PRODUCER;
    }

    public static KafkaProducer<String, KafkaSerializable> getCustomEntityProducer(KafkaProducerType producerType) {
        if (CUSTOM_ENTITY_PRODUCER == null) {
            switch (producerType) {
                case CUSTOMER:
                    CUSTOM_ENTITY_PRODUCER = new KafkaProducer<>(customerProducerProps());
                    break;
                default:
                    CUSTOM_ENTITY_PRODUCER = new KafkaProducer<>(customerProducerProps());
            }
        }
        return CUSTOM_ENTITY_PRODUCER;
    }

}
