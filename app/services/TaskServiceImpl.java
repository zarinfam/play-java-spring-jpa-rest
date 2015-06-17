package services;

import daos.TaskDao;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 9/March/15 AD.
 */

@Named
@Transactional
public class TaskServiceImpl implements TaskService{

    @Inject
    private TaskDao taskDao;

    @Override
    public void addTask(Task task) {
        taskDao.persist(task);
    }

    @Override
    public Task editTask(Task task) {
        return taskDao.merge(task);
    }

    @Override
    public Task getTask(Long taskId) {
        return taskDao.find(taskId, Task.class);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskDao.remove(taskId, Task.class);
    }

    @Override
    public List<Task> listTasks() {
        return taskDao.getAll(Task.class);
    }
}
