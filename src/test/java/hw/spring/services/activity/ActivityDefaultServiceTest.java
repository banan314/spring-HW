package hw.spring.services.activity;

import hw.spring.controllers.UserActivitiesController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by kamil on 23.04.17.
 */
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