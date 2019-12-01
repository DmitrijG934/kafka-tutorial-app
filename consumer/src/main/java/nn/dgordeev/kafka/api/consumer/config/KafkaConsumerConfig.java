package nn.dgordeev.kafka.api.consumer.config;

import nn.dgordeev.kafka.api.common.model.custom.Customer;
import nn.dgordeev.kafka.api.consumer.deserializer.CustomerDeserializer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class KafkaConsumerConfig {
    private static KafkaConsumer<String, Customer> INSTANCE;
    private static final String KEY_DESERIALIZER = "key.deserializer";
    private static final String VALUE_DESERIALIZER = "value.deserializer";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String KAFKA_SERVER = "localhost:9092";
    private static final String GROUP_ID = "group.id";

    private KafkaConsumerConfig() {
    }

    private static Properties consumerProperties() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS, KAFKA_SERVER);
        properties.put(KEY_DESERIALIZER, StringDeserializer.class);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put(VALUE_DESERIALIZER, CustomerDeserializer.class);
        properties.put(GROUP_ID, "customer_group");
        return properties;
    }

    public static KafkaConsumer<String, Customer> getInstance() {
        System.out.println(String.format("Kafka Consumer instance has been initialized with props: %s.", consumerProperties()));
        if (INSTANCE == null) {
            INSTANCE = new KafkaConsumer<>(consumerProperties());
        }
        return INSTANCE;
    }
}
