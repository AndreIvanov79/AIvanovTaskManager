package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;

import java.util.ArrayList;

public class TaskServiceImpl {
    public boolean addTaskToUser(User user, Task task){
        user.getMyTasks().add(task);
        user.setTaskCounter();
        return true;
    }

    public void showUserTasks(User user){
        for (Task task: user.getMyTasks()) {
            System.out.println(user.getUserName()+" has "+task.toString());
        }
    }
}
