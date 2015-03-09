package controllers;

import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import play.*;
import play.libs.Json;
import play.mvc.*;

import play.mvc.Controller;
import services.TaskService;
import views.html.*;


@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    private TaskService taskService;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result listTasks() {
        return ok(Json.toJson(taskService.listTasks()));
    }

    public Result getTask(Long taskId) {
        return ok(Json.toJson(taskService.getTask(taskId)));
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
