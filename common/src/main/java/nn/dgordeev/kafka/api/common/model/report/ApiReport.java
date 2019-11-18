package nn.dgordeev.kafka.api.common.model.report;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiReport implements KafkaSerializable {
    private String message;
    private Exception error;

    @Override
    public KafkaProducerType getProducerType() {
        return KafkaProducerType.ERROR_REPORTING;
    }
}
