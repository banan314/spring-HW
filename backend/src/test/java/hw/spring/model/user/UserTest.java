package hw.spring.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.helpers.UserTestHelper;
import hw.spring.model.activity.Activity;
import hw.spring.services.activity.ActivityDefaultServiceTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserTest extends UserTestHelper {
    private List<Activity> mockActivities = new ArrayList<>();

    public void givenSexAndDate_whenSerializing_thenReadable() throws Exception {
        User user = fakeUser();

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("FEMALE"));
        assertThat(result, containsString("1994"));
        assertThat(result, containsString("3"));
        assertThat(result, containsString("20"));
    }

    public void givenActivities_whenSerializing_thenReadableOutput() throws Exception {
        User user = fakeUser();

        createMockActivities();
        mockActivities.stream().skip(1).forEach(a -> user.addActivity(a));

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("\"exercising\",\"learning\""));
    }

    private void createMockActivities() {
        ActivityDefaultServiceTest activitiesCreator = new ActivityDefaultServiceTest();
        activitiesCreator.init();

        mockActivities.addAll(activitiesCreator.getMockActivities());
    }
}