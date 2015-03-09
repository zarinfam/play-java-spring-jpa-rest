import configs.AppConfig;
import models.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import services.TaskService;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

@ContextConfiguration(classes={AppConfigTest.class})
public class TaskServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private TaskService taskService;

    @Test
    public void createTask() {
        Task bar = new Task("task1");
        taskService.addTask(bar);
        assertThat(bar.getId()).isNotNull();
    }


}