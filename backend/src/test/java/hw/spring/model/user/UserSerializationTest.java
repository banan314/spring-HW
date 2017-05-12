package hw.spring.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.model.Activity;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kamil on 26.04.17.
 */
public class UserSerializationTest {
    List<Activity> mockActivities = new ArrayList<Activity>();

    private void createMockActivities() {
        mockActivities.addAll(Arrays.asList(
                new Activity("lecturing", LocalDate.of(2016, 3, 20)),
                new Activity("exercising", LocalDate.of(2015, 12, 24)),
                new Activity("learning", LocalDate.of(2012, 9, 1)))
        );
    }

    @Test
    public void givenSexAndDate_whenSerializing_thenReadable() throws Exception {
        User user = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("FEMALE"));
        assertThat(result, containsString("1994,3,20"));
    }

    @Test
    public void givenActivities_whenSerializing_thenReadableOutput() throws Exception {
        User user = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");
        createMockActivities();
        mockActivities.stream().skip(1).forEach(a -> user.addActivity(a));

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("\"exercising\",\"learning\""));
    }
}