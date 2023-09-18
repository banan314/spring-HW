package hw.spring.controllers;

import hw.spring.model.activity.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CheckInController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name.check-in}")
    private String checkInTopicName;

    @PostMapping("/check-in")
    public void checkIn(@RequestBody Activity activity) {
        String message = "checked in %s in %s starting at %s"
                .formatted(activity.getName(), activity.getLocation(), activity.getStartDate());
        kafkaTemplate.send(checkInTopicName, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) log.info("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    else
                        log.error("Unable to send message=[" +
                                message + "] due to : " + ex.getMessage());
                });
    }
}
