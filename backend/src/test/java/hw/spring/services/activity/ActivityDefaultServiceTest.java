package hw.spring.services.activity;

import hw.spring.model.activity.Activity;
import hw.spring.model.repositories.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by kamil on 23.04.17.
 */
@SpringBootTest
public class ActivityDefaultServiceTest {

    private final List<Activity> mockActivities = new ArrayList<>(3);
    @Inject
    private ActivityDefaultService activityDefaultService;
    private ActivityDefaultService serviceNotAutowired;
    @Mock
    private ActivityRepository repository;

    public List<Activity> getMockActivities() {
        return mockActivities;
    }

    @BeforeEach
    public void setUp() {
        serviceNotAutowired = new ActivityDefaultService();
        serviceNotAutowired.activityRepository = repository;
        init();
    }

    public void init() {
        if (mockActivities.isEmpty()) createMockActivities();
    }


    private void createMockActivities() {
        mockActivities.addAll(Arrays.asList(new Activity("lecturing", Date.valueOf("2016-3-20")),
                new Activity("exercising", Date.valueOf("2015-12-24")),
                new Activity("learning", Date.valueOf("2012-9-1"))));
        mockActivities.stream().forEach(activity -> activity.setLocation("Politechnika Rzeszowska"));
    }

    @Test
    public void shouldServiceBeNotNull() throws Exception {
        assertNotNull(activityDefaultService);
        assertNotNull(activityDefaultService.activityRepository);
    }

    @Test
    public void getAll() throws Exception {
        when(repository.findAll()).thenReturn(mockActivities);
        when(repository.findAllByOrderById()).thenReturn(mockActivities);

        List<Activity> activities = serviceNotAutowired.getAll();

        assertEquals(3, activities.size());
    }

    @Test
    public void addActivity() throws Exception {
        Activity activity = new Activity("lecturing", Date.valueOf("2016-3-20"));

        serviceNotAutowired.addActivity(activity);
        verify(repository).save(ArgumentMatchers.<Activity>notNull());
    }

    @Test
    public void deleteActivity() throws Exception {
        Mockito.when(repository.existsById(10)).thenReturn(true);
        serviceNotAutowired.deleteActivity(10);
        verify(repository).deleteById(10);
    }

}