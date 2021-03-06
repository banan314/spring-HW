package hw.spring.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by kamil on 30.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Inject
    private MockMvc mvc;

    @Test
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/activities")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}