package hw.spring.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.model.Activity;
import hw.spring.services.activity.ActivityDefaultServiceTest;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by kamil on 26.04.17.
 */
public class UserSerializationTest {
    List<Activity> mockActivities = new ArrayList<Activity>();

    private void createMockActivities() {
        ActivityDefaultServiceTest activitiesCreator = new ActivityDefaultServiceTest();
        activitiesCreator.init();

        mockActivities.addAll(activitiesCreator.getMockActivities());
    }

    @Test
    public void givenSexAndDate_whenSerializing_thenReadable() throws Exception {
        User user = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, Date.valueOf("1994-3-20"),
                "Joe Staunton");

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("FEMALE"));
        assertThat(result, containsString("1994,3,20"));
    }

    @Test
    public void givenActivities_whenSerializing_thenReadableOutput() throws Exception {
        User user = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, Date.valueOf("1994-3-20"),
                "Joe Staunton");
        createMockActivities();
        mockActivities.stream().skip(1).forEach(a -> user.addActivity(a));

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("\"exercising\",\"learning\""));
    }
}