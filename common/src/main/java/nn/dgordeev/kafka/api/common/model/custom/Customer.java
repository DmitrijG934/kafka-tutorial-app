package nn.dgordeev.kafka.api.common.model.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import nn.dgordeev.kafka.api.common.model.BaseEntity;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.common.utils.ViewUtils;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity implements KafkaSerializable {
    @NonNull
    @JsonProperty(value = "customer_name")
    private String name;
    @Override
    @JsonIgnore
    public KafkaProducerType getProducerType() {
        return KafkaProducerType.CUSTOMER;
    }

    @Override
    @JsonView(value = ViewUtils.CustomerIdView.class)
    @JsonProperty(value = "customer_id")
    public UUID getId() {
        return super.getId();
    }
}
