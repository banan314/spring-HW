package hw.spring.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by kamil on 26.04.17.
 */
public class ActivityTest {
    @Test
    public void givenLocalDate_whenUsingSerializer_thenReadableOutput() throws Exception {
        Activity activity = new Activity("cycling", Date.valueOf("2016-4-25"));

        String result = new ObjectMapper().writeValueAsString(activity);

        assertThat("date should be serialized as an array of numbers - containg the year", result,
                containsString("2016"));
        assertThat("date should be serialized as an array of numbers - containg the month", result,
                containsString("4"));
        assertThat("date should be serialized as an array of numbers - containg the day", result,
                containsString("25"));
    }
}