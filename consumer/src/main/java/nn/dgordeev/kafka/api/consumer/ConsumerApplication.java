package nn.dgordeev.kafka.api.consumer;

import nn.dgordeev.kafka.api.common.model.custom.Customer;
import nn.dgordeev.kafka.api.consumer.config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;

import static nn.dgordeev.kafka.api.common.utils.ApiConstants.CUSTOMER_TOPIC_NAME;

public class ConsumerApplication {
    public static void main(String[] args) {
        KafkaConsumer<String, Customer> consumer = KafkaConsumerConfig.getInstance();
        try (consumer) {
            consumer.subscribe(List.of(CUSTOMER_TOPIC_NAME));
            System.out.println("Initializing Kafka consumer session loop...");
            while (true) {
                ConsumerRecords<String, Customer> records = consumer.poll(100);
                for (ConsumerRecord<String, Customer> record : records) {
                    System.out.println(String.format("%s", record.value()));
                }
            }
        }
    }
}
