package hw.spring.helpers;

import hw.spring.model.activity.Activity;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ActivityTestHelper {
    public List<Activity> mockActivities() {
        List<Activity> mockActivities = Arrays.asList(new Activity("lecturing", Date.valueOf("2016-3-20")),
                new Activity("exercising", Date.valueOf("2015-12-24")),
                new Activity("learning", Date.valueOf("2012-9-1")));

        mockActivities.forEach(activity -> activity.setLocation("Politechnika Rzeszowska"));

        return mockActivities;
    }
}
