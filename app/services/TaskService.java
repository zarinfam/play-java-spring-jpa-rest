package services;

import models.Task;

import java.util.List;

/**
 * Created by saeed on 9/March/15 AD.
 */


public interface TaskService {
    void addTask(Task task);

    Task editTask(Task task);

    Task getTask(Long taskId);

    void deleteTask(Long taskId);

    List<Task> listTasks();

}
