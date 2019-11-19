package nn.dgordeev.kafka.api.common.model;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public abstract class BaseEntity {
    @NonNull
    protected UUID id = UUID.randomUUID();
}
