package nn.dgordeev.kafka.api.common.model.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nn.dgordeev.kafka.api.common.model.BaseEntity;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaProducerType;
import nn.dgordeev.kafka.api.common.model.kafka.KafkaSerializable;
import nn.dgordeev.kafka.api.common.utils.ViewUtils;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"item_id", "item_name", "createdAt", "owner"})
public class Item extends BaseEntity implements KafkaSerializable {
    @NonNull
    @JsonProperty(value = "item_name")
    private String caption;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @NonNull
    @JsonView(value = ViewUtils.CustomerIdView.class)
    private Customer owner;

    @Override
    @JsonIgnore
    public KafkaProducerType getProducerType() {
        return KafkaProducerType.ITEM;
    }

    @Override
    @JsonProperty(value = "item_id")
    public UUID getId() {
        return super.getId();
    }
}
