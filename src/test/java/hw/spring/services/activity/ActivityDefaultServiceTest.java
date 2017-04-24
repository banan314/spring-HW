package hw.spring.services.activity;

import hw.spring.controllers.UserActivitiesController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by kamil on 23.04.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityDefaultServiceTest {

    @Autowired
    private ActivityDefaultService activityDefaultService;

    @Test
    public void shouldServiceBeNotNull() throws Exception {
        assertNotNull(activityDefaultService);
        assertNotNull(activityDefaultService.activityRepository);
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void addActivity() throws Exception {
    }

    @Test
    public void deleteActivity() throws Exception {
    }

}