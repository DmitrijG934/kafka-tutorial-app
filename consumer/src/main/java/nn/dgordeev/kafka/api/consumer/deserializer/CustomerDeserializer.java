package nn.dgordeev.kafka.api.consumer.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import nn.dgordeev.kafka.api.common.model.custom.Customer;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Customer deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = null;
        try {
            customer = objectMapper.readValue(data, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void close() {

    }
}
