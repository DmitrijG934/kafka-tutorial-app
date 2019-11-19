package nn.dgordeev.kafka.api.producer;

import nn.dgordeev.kafka.api.common.model.custom.Customer;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.producer.factory.DefaultKafkaProducerFactory;
import nn.dgordeev.kafka.api.producer.service.KafkaProducerService;
import nn.dgordeev.kafka.api.producer.service.entity.AsyncEntityKafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;

import static nn.dgordeev.kafka.api.common.utils.ApiConstants.CUSTOMER_TOPIC_NAME;

public class ProducerApplication {
    public static void main(String[] args) {

        // customer
        KafkaProducerService<String, KafkaSerializable> producerService =
                new AsyncEntityKafkaProducerService(DefaultKafkaProducerFactory.getCustomEntityProducer(KafkaProducerType.CUSTOMER));

        Customer customerToTopic = Customer.builder()
                .name("jack wilson")
                .build();

        ProducerRecord<String, KafkaSerializable> producerRecord = new ProducerRecord<>(CUSTOMER_TOPIC_NAME, "consumer", customerToTopic);
        producerService.send(producerRecord);

        // item
//        KafkaProducerService<String, KafkaSerializable> itemProducerService =
//                new AsyncEntityKafkaProducerService(DefaultKafkaProducerFactory.getCustomEntityProducer(KafkaProducerType.ITEM));
//
//        KafkaSerializable itemToTopic = Item.builder()
//                .createdAt(new Date())
//                .owner(customerToTopic)
//                .caption("test")
//                .build();
//
//        ProducerRecord<String, KafkaSerializable> itemRecord = new ProducerRecord<>(ITEMS_TOPIC_NAME, "item", itemToTopic);
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("How much events would you like to send? ");
//        String messageReplicas = scanner.nextLine();
//        if (!messageReplicas.equalsIgnoreCase("q")) {
//            int timesToSend = Integer.parseInt(messageReplicas);
//            if (timesToSend <= 100) {
//                for (int i = 0; i < timesToSend; i++) {
//                    System.out.println("Sending...");
//                    itemProducerService.send(itemRecord);
//                }
//            }
//        }
//        itemProducerService.send(itemRecord);
    }
}