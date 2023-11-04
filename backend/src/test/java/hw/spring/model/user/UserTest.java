package hw.spring.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.helpers.ActivityTestHelper;
import hw.spring.helpers.UserTestHelper;
import hw.spring.model.activity.Activity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


class UserTest extends UserTestHelper {
    private final List<Activity> mockActivities = new ArrayList<>();

    @Test
    void givenSexAndDate_whenSerializing_thenReadable() throws Exception {
        User user = fakeUser();

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("FEMALE"));
        assertThat(result, containsString("1994"));
        assertThat(result, containsString("3"));
        assertThat(result, containsString("20"));
    }

    @Test
    void givenActivities_whenSerializing_thenReadableOutput() throws Exception {
        User user = fakeUser();

        createMockActivities();
        mockActivities.stream()
                .skip(1)
                .forEach(user::addActivity);

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("\"exercising\",\"learning\""));
    }

    @Test
    void createMockActivities() {
        mockActivities.addAll(ActivityTestHelper.mockActivities());
    }
}