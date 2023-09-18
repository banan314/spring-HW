package hw.spring.controllers;

import hw.spring.model.activity.Activity;
import hw.spring.services.attendance.ActivityAttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActivityAttendanceController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ActivityAttendanceService activityAttendanceService;

    @Value("${topic.name.activity-attendance}")
    private String activityAttendanceTopicName;

    @GetMapping("/activity/attendance")
    public List<String> getActivityAttendance() {
        return activityAttendanceService.getActivityAttendance();
    }

    @PostMapping("/activity/attendance")
    public void registerActivityAttendance(@RequestBody Activity activity) {
        final String message = "checked out";
        kafkaTemplate.send(activityAttendanceTopicName, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) log.info("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    else
                        log.error("Unable to send message=[" +
                                message + "] due to : " + ex.getMessage());
                });
    }
}
