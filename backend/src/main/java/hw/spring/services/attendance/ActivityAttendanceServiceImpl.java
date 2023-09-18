package hw.spring.services.attendance;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityAttendanceServiceImpl implements ActivityAttendanceService {

    private final List<String> activityAttendance = new ArrayList<>();

    @KafkaListener(topics = {"${topic.name.check-in}", "${topic.name.check-out}"}, groupId = "${topic.groupId}")
    public void listenGroupCheck(String message) {
        activityAttendance.add(message);
    }

    @Override
    public List<String> getActivityAttendance() {
        return activityAttendance;
    }
}
