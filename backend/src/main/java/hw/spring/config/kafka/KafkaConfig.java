package hw.spring.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${topic.name.check-in")
    private String checkInTopicName;

    @Value("${topic.name.check-out")
    private String checkOutTopicName;

    @Value("${topic.name.activity-attendance")
    private String activityAttendanceTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic checkInTopic() {
        return new NewTopic(checkInTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic checkOutTopic() {
        return new NewTopic(checkOutTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic activityAttendanceTopic() {
        return new NewTopic(activityAttendanceTopicName, 1, (short) 1);
    }
}
