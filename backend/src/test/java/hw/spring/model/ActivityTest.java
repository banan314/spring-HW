package hw.spring.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.*;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by kamil on 26.04.17.
 */
public class ActivityTest {
    @Test
    public void givenLocalDate_whenUsingSerializer_thenReadableOutput() throws Exception {
        Activity activity = new Activity("cycling", Date.valueOf("2016-4-25"));

        String result = new ObjectMapper().writeValueAsString(activity);

        assertThat("local date should be serialized as an array of numbers", result, containsString("[2016,4,25]"));
    }
}