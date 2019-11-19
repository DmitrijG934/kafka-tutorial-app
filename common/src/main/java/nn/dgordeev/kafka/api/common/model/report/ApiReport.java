package nn.dgordeev.kafka.api.common.model.report;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiReport {
    private String message;
    private Exception error;
}
