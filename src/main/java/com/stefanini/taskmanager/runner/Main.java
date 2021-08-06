package com.stefanini.taskmanager.runner;

import com.stefanini.taskmanager.utilities.Parser;
import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.utilities.Manager;
import com.stefanini.taskmanager.utilities.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = LogManager.getLogManager().getLogger("Main");
    public static void main(String[] args) throws IOException {
        User user1=new User("John","Smith","Johnny",new LinkedList<Task>());
        User user2=new User("Carl","White","Carlik",new LinkedList<Task>());
        User user3=new User("Tom","Black","Tommy",new LinkedList<Task>());
        User user4=new User("Edward","Woodston","Edik",new LinkedList<Task>());

        Task task1=new Task("Delivery","To carry some package to address...");
        Task task2=new Task("Building","To build the small building...");
        Task task3=new Task("Jogging","To run from here to ...");
        Task task4=new Task("Eating","To eat and to drink ...");

        Manager manager=new Manager();
        manager.addTaskToUser(user1,task2);
        manager.addTaskToUser(user2,task4);
        manager.addTaskToUser(user2,task2);
        manager.addTaskToUser(user2,task3);
        manager.addTaskToUser(user3,task3);
        manager.addTaskToUser(user3,task1);
        manager.addTaskToUser(user4,task2);
        manager.addTaskToUser(user4,task1);
        ArrayList<User> amountOfUsers=new ArrayList<>();

        manager.addUserToList(user1,amountOfUsers);
        manager.addUserToList(user2,amountOfUsers);
        manager.addUserToList(user3,amountOfUsers);
        manager.addUserToList(user4,amountOfUsers);
        manager.showAllUsers(amountOfUsers);

        manager.showUserTasks(user1);
        manager.showUserTasks(user2);
        manager.showUserTasks(user3);
        manager.showUserTasks(user4);

        Serializer serializer=new Serializer();
        serializer.serialization("src/main/resources/file.txt",user1.toString(),user2.toString(),user3.toString(),user4.toString());

        Parser parser=new Parser();
        parser.parseFromFile("src/main/resources/file.txt");
    }
}
