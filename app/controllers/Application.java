package controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import daos.GeneralDao;
import daos.GenericDao;
import models.Role;
import models.Task;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.libs.Json;
import play.mvc.*;

import play.mvc.Controller;
import services.TaskService;
import services.UserService;
import views.html.*;

import java.io.IOException;
import java.util.List;


@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GeneralDao generalDao;

    @Autowired
    private UserService userService;

    @BodyParser.Of(BodyParser.Json.class)
    public Result editRolesOfUser(long userId) throws IOException {
        List<Long> roleIds = new ObjectMapper().readValue(Controller.request().body().asJson().toString(),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Long.class));

        userService.updateUserRoles(userId, roleIds);
        return Results.ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        User user = Json.fromJson(Controller.request().body().asJson(), User.class);
        if (user.getId() != null) {
            return badRequest();
        }
        userService.addUser(user);
        return ok();
    }

    public Result assignRoleToUser(long userId, long roleId) {

        userService.setRoleForUser(userId, roleId);
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)

    public Result createRole() {
        Role role = Json.fromJson(Controller.request().body().asJson(), Role.class);

        userService.addRole(role);
        return ok();
    }


    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result listTasks() {
        return ok(Json.toJson(taskService.listTasks()));
    }

    public Result getTask(Long taskId) {
//        return ok(Json.toJson(taskService.getTask(taskId)));
        return ok(Json.toJson(generalDao.find(taskId, Task.class)));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createTask() {
        Task task = Json.fromJson(Controller.request().body().asJson(), Task.class);
        taskService.addTask(task);
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result editTask(Long taskId) {
        Task task = Json.fromJson(Controller.request().body().asJson(), Task.class);
        return Results.ok(Json.toJson(taskService.editTask(task)));
    }

    public Result deleteTask(Long taskId) {
        taskService.deleteTask(taskId);
        return Results.ok();
    }


}
