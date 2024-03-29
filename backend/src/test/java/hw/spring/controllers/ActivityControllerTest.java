package hw.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hw.spring.model.activity.Activity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
class ActivityControllerTest {

    private final ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
    @Inject
    private MockMvc mvc;
    @Inject
    private ActivityController activityController;

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/activities")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handleNotFoundResource() {
        assertEquals(HttpStatus.NOT_FOUND, activityController.handleNotFoundResource());
    }

    @Test
    void handleBadRequest() {
        assertEquals(HttpStatus.BAD_REQUEST, activityController.handleBadRequest());
    }

    @Test
    void getActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/activities/2"));
    }

    @Test
    void create() throws Exception {
        Activity activity = new Activity("cycling", Date.valueOf("2016-4-25"));

        mvc.perform(MockMvcRequestBuilders.post("/activities")
                .content(mapper.writeValueAsString(activity))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/users/2"));
    }

    @Test
    void deleteActivity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/2"));
    }
}