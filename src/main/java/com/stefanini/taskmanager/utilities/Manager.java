package com.stefanini.taskmanager.utilities;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;

import java.util.ArrayList;

public class Manager {
    public void addUserToList(User user, ArrayList <User> list){
        list.add(user);
    }

    public void showAllUsers(ArrayList <User> list){
        for (User user: list){
            System.out.println(user.toString());
        }
    }

    public void addTaskToUser(User user, Task task){
        user.getMyTasks().add(task);
        user.setTaskCounter();
    }

    public void showUserTasks(User user){
        for (Task task: user.getMyTasks()) {
            System.out.println(task.toString());
        }
    }
}
