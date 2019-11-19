package nn.dgordeev.kafka.api.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

import static nn.dgordeev.kafka.api.common.utils.ApiConstants.CUSTOMER_TOPIC_NAME;

@Slf4j
public class CustomerPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(CUSTOMER_TOPIC_NAME);
        int amountPartitions = partitions.size();
        if ((keyBytes) == null || !(key instanceof String)) {
            throw new InvalidRecordException("Invalid producer record key.");
        }
        if(key.equals("CustomerJourney")) {
            return amountPartitions - 1;
        }
        return (Math.abs(Utils.murmur2(keyBytes)) % (amountPartitions - 1));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}