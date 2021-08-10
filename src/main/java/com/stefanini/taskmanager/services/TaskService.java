package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;

public interface TaskService {
    public boolean addTaskToUser(User user, Task task);
    public void showUserTasks(User user);
}
