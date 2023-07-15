package hw.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hw.spring.model.activity.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamil on 30.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Inject
    private MockMvc mvc;

    @Inject
    private ActivityController activityController;
    private ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/activities")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void handleNotFoundResource() {
        assertEquals(HttpStatus.NOT_FOUND, activityController.handleNotFoundResource());
    }

    @Test
    public void handleBadRequest() {
        assertEquals(HttpStatus.BAD_REQUEST, activityController.handleBadRequest());
    }

    @Test
    public void getActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/activities/2"));
    }

    @Test
    public void create() throws Exception {
        Activity activity = new Activity("cycling", Date.valueOf("2016-4-25"));

        mvc.perform(MockMvcRequestBuilders.post("/activities")
                .content(this.mapper.writeValueAsString(activity))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/users/2"));
    }

    @Test
    public void deleteActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/2"));
    }
}