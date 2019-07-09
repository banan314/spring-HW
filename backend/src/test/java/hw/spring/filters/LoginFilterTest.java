package hw.spring.filters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginFilterTest {
    @Inject
    private MockMvc mockMvc;

    @Test
    public void testAuthentication() throws Exception {
        final String postBodyContent = "{\"username\": \"Banan\",\"email\": \"bla@gmail.com\",\"password\": \"costam\"}";

        //create a user
        mockMvc.perform(post("/users/new")
            .content(postBodyContent))
            .andExpect(status().isOk());

        //check if the user can log in
        mockMvc.perform(post("/login")
            .content(postBodyContent))
            .andExpect(status().isOk());
    }
}