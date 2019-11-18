package nn.dgordeev.kafka.api.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements KafkaSerializable {
    @Getter
    @JsonProperty(value = "customer_id")
    private UUID id;
    @JsonProperty(value = "customer_name")
    private String name;

    @Override
    @JsonIgnore
    public KafkaProducerType getProducerType() {
        return KafkaProducerType.CUSTOMER;
    }
}
