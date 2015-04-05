import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import daos.GeneralDao;
import models.Role;
import models.RoleOfUser;
import models.Task;
import models.User;
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
import services.UserService;

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

    @Test
    public void saveUser() {
        GlobalTest globalSettings = new GlobalTest();
        start(fakeApplication(inMemoryDatabase(), globalSettings));

        User user = new User();
        user.setName("Mohsen");
        //do test
        Result result = route(fakeRequest(POST, "/users").withJsonBody(Json.toJson(user)));

        assertThat(status(result)).isEqualTo(OK);

    }

    @Test
    public void setRoleForUser() {
        GlobalTest globalSettings = new GlobalTest();
        start(fakeApplication(inMemoryDatabase(), globalSettings));
        User user = new User();
        user.setName("Mohsen");
        globalSettings.applicationContext.getBean(UserService.class).addUser(user);

        Role role = new Role();
        role.setName("role1");
        globalSettings.applicationContext.getBean(UserService.class).addRole(role);

        Result result = route(fakeRequest(POST, "/users/" + user.getId() + "/roles/" + role.getId()));

        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void editUserRoles() {
        GlobalTest globalSettings = new GlobalTest();
        start(fakeApplication(inMemoryDatabase(), globalSettings));
        User user = new User();
        user.setName("Mohsen");
        globalSettings.applicationContext.getBean(UserService.class).addUser(user);

        Role role = new Role();
        role.setName("role1");
        globalSettings.applicationContext.getBean(UserService.class).addRole(role);

        Role role2 = new Role();
        role2.setName("role2");
        globalSettings.applicationContext.getBean(UserService.class).addRole(role2);

        List<Long> roles = new ArrayList<>();
        roles.add(role.getId());
        roles.add(role2.getId());
//        long beforeSaveCount = globalSettings.applicationContext.getBean(GeneralDao.class).count(RoleOfUser.class);
        Result result = route(fakeRequest(PUT, "/users/" + user.getId() + "/roles")
                .withJsonBody(Json.toJson(roles)));

        assertThat(status(result)).isEqualTo(OK);
        long currentCount = globalSettings.applicationContext.getBean(GeneralDao.class).count(RoleOfUser.class);

        assertThat(currentCount).isEqualTo(2);

    }

}
