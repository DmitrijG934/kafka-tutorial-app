package nn.dgordeev.kafka.api.common.utils;

import nn.dgordeev.kafka.api.common.model.report.ApiReport;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaUtils {
    private KafkaUtils() {}
    public static void processKafkaMetadata(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            System.out.println(ApiReport.builder().error(e).message("Error happened during kafka event sending.").build());
        }
        if (recordMetadata != null) {
            System.out.println("Sent to the topic: " + recordMetadata.topic());
            System.out.println("With offset: " + recordMetadata.offset());
        }
    }
}
