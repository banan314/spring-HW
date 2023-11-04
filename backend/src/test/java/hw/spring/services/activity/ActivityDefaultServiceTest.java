package hw.spring.services.activity;

import hw.spring.helpers.ActivityTestHelper;
import hw.spring.model.activity.Activity;
import hw.spring.model.repositories.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Profile("test")
@SpringBootTest({"spring.profiles.active=test"})
class ActivityDefaultServiceTest {

    private List<Activity> mockActivities = new ArrayList<>(3);

    @Inject
    private ActivityDefaultService activityDefaultService;
    private ActivityDefaultService serviceNotAutowired;

    @Mock
    private ActivityRepository repository;

    @BeforeEach
    public void setUp() {
        serviceNotAutowired = new ActivityDefaultService();
        serviceNotAutowired.activityRepository = repository;
        init();
    }

    private void init() {
        mockActivities = ActivityTestHelper.mockActivities();
    }

    @Test
    void shouldServiceBeNotNull() throws Exception {
        assertNotNull(activityDefaultService);
        assertNotNull(activityDefaultService.activityRepository);
    }

    @Test
    void getAll() throws Exception {
        when(repository.findAllByOrderById()).thenReturn(mockActivities);

        List<Activity> activities = serviceNotAutowired.getAll();

        assertEquals(mockActivities.size(), activities.size());
    }

    @Test
    void addActivity() throws Exception {
        Activity activity = new Activity("lecturing", Date.valueOf("2016-3-20"));

        serviceNotAutowired.addActivity(activity);
        verify(repository).save(ArgumentMatchers.<Activity>notNull());
    }

    @Test
    void deleteActivity() throws Exception {
        Mockito.when(repository.existsById(10)).thenReturn(true);
        serviceNotAutowired.deleteActivity(10);
        verify(repository).deleteById(10);
    }
}