package nn.dgordeev.kafka.api.producer.factory;

import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.producer.config.CustomerPartitioner;
import nn.dgordeev.kafka.api.producer.serializer.CustomerSerializer;
import nn.dgordeev.kafka.api.producer.serializer.ItemSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DefaultKafkaProducerFactory {
    private static KafkaProducer<String, String> STRING_KAFKA_PRODUCER;
    private static KafkaProducer<String, KafkaSerializable> CUSTOM_ENTITY_PRODUCER;
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String PARTITIONER_CLASS = "partitioner.class";
    private static final String KAFKA_SERVER = "localhost:9092";

    private static Properties defaultProducerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, KAFKA_SERVER);
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, StringSerializer.class);
        return properties;
    }

    private static Properties customerProducerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, KAFKA_SERVER);
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, CustomerSerializer.class);
        properties.put(PARTITIONER_CLASS, CustomerPartitioner.class);
        return properties;
    }

    private static Properties itemProducerProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, KAFKA_SERVER);
        properties.put(KEY_SERIALIZER, StringSerializer.class);
        properties.put(VALUE_SERIALIZER, ItemSerializer.class);
        return properties;
    }

    public static KafkaProducer<String, String> getStringProducer() {
        if (STRING_KAFKA_PRODUCER == null) {
            STRING_KAFKA_PRODUCER = new KafkaProducer<>(defaultProducerProps());
        }
        return STRING_KAFKA_PRODUCER;
    }

    public static KafkaProducer<String, KafkaSerializable> getCustomEntityProducer(KafkaProducerType producerType) {
            switch (producerType) {
                case CUSTOMER:
                    CUSTOM_ENTITY_PRODUCER = new KafkaProducer<>(customerProducerProps());
                    break;
                case ITEM:
                    CUSTOM_ENTITY_PRODUCER = new KafkaProducer<>(itemProducerProps());
                    break;
            }
        return CUSTOM_ENTITY_PRODUCER;
    }

}
