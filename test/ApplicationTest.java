import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import models.Task;
import org.junit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import play.Application;
import play.libs.Json;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import services.TaskService;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


public class ApplicationTest {

    @Test
    public void tasksRoute() throws Exception {

        //initial test environment
        GlobalTest globalSettings = new GlobalTest();
        start(fakeApplication(inMemoryDatabase(), globalSettings));

        globalSettings.applicationContext.getBean(TaskService.class).addTask(new Task("task1"));

        //do test
        Result result = route(fakeRequest(GET, "/tasks"));

        //assertions
        assertThat(status(result)).isEqualTo(OK);

        List<Task> tasks = new ObjectMapper().readValue(contentAsString(result),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Task.class));

        assertThat(tasks.size()).isEqualTo(1);
    }

}
