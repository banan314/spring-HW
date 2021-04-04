package hw.spring.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.model.activity.Activity;
import hw.spring.helpers.UserTestHelper;
import hw.spring.services.activity.ActivityDefaultServiceTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by kamil on 26.04.17.
 */
public class UserTest extends UserTestHelper {
    List<Activity> mockActivities = new ArrayList<Activity>();

    private void createMockActivities() {
        ActivityDefaultServiceTest activitiesCreator = new ActivityDefaultServiceTest();
        activitiesCreator.init();

        mockActivities.addAll(activitiesCreator.getMockActivities());
    }

    @Test
    public void givenSexAndDate_whenSerializing_thenReadable() throws Exception {
        User user = fakeUser();

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("FEMALE"));
        assertThat(result, containsString("1994"));
        assertThat(result, containsString("3"));
        assertThat(result, containsString("20"));
    }

    @Test
    public void givenActivities_whenSerializing_thenReadableOutput() throws Exception {
        User user = fakeUser();

        createMockActivities();
        mockActivities.stream().skip(1).forEach(a -> user.addActivity(a));

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("\"exercising\",\"learning\""));
    }
}