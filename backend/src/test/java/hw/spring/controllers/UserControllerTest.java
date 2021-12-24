package hw.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hw.spring.helpers.UserTestHelper;
import hw.spring.model.user.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamil on 30.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends UserTestHelper {

    @Inject
    private MockMvc mvc;

    @Inject
    private UserController userController;

    private ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    public void getAllUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void handleNotFoundResource() {
        assertEquals(HttpStatus.NOT_FOUND, userController.handleNotFoundResource());
    }

    @Test
    public void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/2"));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    public void post() throws Exception {
        User user = fakeUser();

        mvc.perform(MockMvcRequestBuilders.post("/users/new")
                .content(this.mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    public void put() throws Exception {
        User user = fakeUser();

        mvc.perform(MockMvcRequestBuilders.put("/users/2")
                .content(this.mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    public void deleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/users/2"));
    }

    @Test
    @Ignore
    public void deleteAll() {
    }
}