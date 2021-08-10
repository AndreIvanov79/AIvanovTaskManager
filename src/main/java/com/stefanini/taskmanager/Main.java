package com.stefanini.taskmanager;

import com.stefanini.taskmanager.db.daoImpl.DAOTask;
import com.stefanini.taskmanager.db.daoImpl.DAOUser;
import com.stefanini.taskmanager.services.impl.TaskServiceImpl;
import com.stefanini.taskmanager.services.impl.UserServiceImpl;
import com.stefanini.taskmanager.utilities.Parser;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.utilities.Serializer;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        User user1=new User("John","Smith","Johnny",new ArrayList<Task>());
        User user2=new User("Carl","White","Carlik",new ArrayList<Task>());
        User user3=new User("Tom","Black","Tommy",new ArrayList<Task>());
        User user4=new User("Edward","Woodston","Edik",new ArrayList<Task>());
        log.info("Users are created");

        Task task1=new Task("Delivery","To carry some package to address...");
        Task task2=new Task("Building","To build the small building...");
        Task task3=new Task("Jogging","To run from here to ...");
        Task task4=new Task("Eating","To eat and to drink ...");
        log.info("Tasks are created");

        TaskServiceImpl taskService=new TaskServiceImpl();
        taskService.addTaskToUser(user1,task2);
        taskService.addTaskToUser(user2,task4);
        taskService.addTaskToUser(user2,task2);
        taskService.addTaskToUser(user2,task3);
        taskService.addTaskToUser(user3,task3);
        taskService.addTaskToUser(user3,task1);
        taskService.addTaskToUser(user4,task2);
        taskService.addTaskToUser(user4,task1);


        taskService.showUserTasks(user1);
        taskService.showUserTasks(user2);
        taskService.showUserTasks(user3);
        taskService.showUserTasks(user4);
        log.info("Tasks of each user presented");
        UserServiceImpl userService=new UserServiceImpl();

        userService.addToList(user1);
        userService.addToList(user2);
        userService.addToList(user3);
        userService.addToList(user4);

        userService.showAllUsers();
        log.info("List of users presented");
        Serializer serializer=new Serializer();

        Parser parser=new Parser();

        User user5=serializer.deserializationFromTerminal(parser.parseFromTerminal(args));

        System.out.println(user5.toString());
        log.info("User from CMD is created");

        DAOUser daoUser=new DAOUser();

        daoUser.deleteAllUsers();
        daoUser.insertUser(user1);
        daoUser.insertUser(user4);
        daoUser.updateUser(user1,"firstName","Mattew");
        daoUser.deleteUserByUserName(user1.getUserName());
        daoUser.deleteUserByID(2);

        DAOTask daoTask=new DAOTask();

        daoTask.deleteAllTasks();
        daoTask.insertTask(task3);
        daoTask.insertTask(task1);
        daoTask.updateTask(task3,"Running");
        daoTask.deleteTaskByTitle("Running");
        daoTask.deleteTaskByID(2);

    }
}
