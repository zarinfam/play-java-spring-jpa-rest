package controllers;

import daos.GeneralDao;
import daos.GenericDao;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import play.libs.Json;
import play.mvc.*;

import play.mvc.Controller;
import services.TaskService;
import views.html.*;


@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GeneralDao generalDao;

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
