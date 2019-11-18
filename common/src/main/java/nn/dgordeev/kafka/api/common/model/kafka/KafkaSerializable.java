package nn.dgordeev.kafka.api.common.model.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface KafkaSerializable {
    @JsonIgnore
    KafkaProducerType getProducerType();
}
