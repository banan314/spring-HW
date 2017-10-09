package hw.spring.services.activity;

import hw.spring.model.Activity;
import hw.spring.repositories.ActivityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.notNull;
import org.mockito.ArgumentMatchers;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by kamil on 23.04.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityDefaultServiceTest {

    @Autowired
    private ActivityDefaultService activityDefaultService;

    private ActivityDefaultService serviceNotAutowired;
    @Mock private ActivityRepository repository;

    @Before
    public void setUp() {
        serviceNotAutowired = new ActivityDefaultService(repository);
    }

    @Test
    public void shouldServiceBeNotNull() throws Exception {
        assertNotNull(activityDefaultService);
        assertNotNull(activityDefaultService.activityRepository);
    }

    @Test
    public void getAll() throws Exception {
        List<Activity> mockActivities = new ArrayList<Activity>();
        mockActivities.addAll(Arrays.asList(
                new Activity("lecturing", LocalDate.of(2016, 3, 20)),
                new Activity("exercising", LocalDate.of(2015, 12, 24)),
                new Activity("learning", LocalDate.of(2012, 9, 1)))
        );
        when(repository.findAll()).thenReturn(mockActivities);

        val activities = serviceNotAutowired.getAll();

        assertEquals(3, activities.size());
    }

    @Test
    public void addActivity() throws Exception {
        val activity = new Activity("lecturing", LocalDate.of(2016, 3, 20));

        serviceNotAutowired.addActivity(activity);
        verify(repository).save(ArgumentMatchers.<Activity>notNull());
    }

    @Test
    public void deleteActivity() throws Exception {
        serviceNotAutowired.deleteActivity(10);
        verify(repository).delete(10);
    }

}