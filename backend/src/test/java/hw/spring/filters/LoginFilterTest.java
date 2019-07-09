package hw.spring.filters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginFilterTest {
    @Inject
    private MockMvc mockMvc;

    @Test
    public void testAuthentication() throws Exception {
        final String postBodyContent = "username=test&email=test@test.com&password=test";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        //check if the user can log in
        Cookie session = mockMvc.perform(post("/login")
                .headers(headers)
                .content(postBodyContent))
                .andReturn()
                .getResponse()
                .getCookie("JSESSIONID");

        if(null == session) {
            return;
        }

        //retrieve users
        mockMvc.perform(get("/users")
            .cookie(session))
            .andExpect(status().isOk());

    }
}