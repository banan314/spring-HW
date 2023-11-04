package hw.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hw.spring.helpers.UserTestHelper;
import hw.spring.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by kamil on 30.05.17.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends UserTestHelper {

    private final ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
    @Inject
    private MockMvc mvc;
    @Inject
    private UserController userController;

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    void getAllUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void handleNotFoundResource() {
        assertEquals(HttpStatus.NOT_FOUND, userController.handleNotFoundResource());
    }

    @Test
    void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/2"));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    void post() throws Exception {
        User user = fakeUser();

        mvc.perform(MockMvcRequestBuilders.post("/users/new")
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    void put() throws Exception {
        User user = fakeUser();

        mvc.perform(MockMvcRequestBuilders.put("/users/2")
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"ADMIN"})
    void deleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/users/2"));
    }

    @Test
    void deleteAll() {
    }
}