package com.stefanini.taskmanager.entities;

import com.stefanini.taskmanager.entities.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private int taskCounter = 0;
    ArrayList<Task> myTasks;

    public User(String firstName, String lastName, String userName,ArrayList<Task> myTasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.myTasks = new ArrayList<Task>();
    }

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User(String userName){
        this.userName=userName;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTaskCounter() {
        return taskCounter;
    }

    public ArrayList<Task> getMyTasks(){
        if (myTasks==null){
        this.myTasks=new ArrayList<Task>();}
        return myTasks;
    }
    public int setTaskCounter(){
        return taskCounter++;
    }

    public static User getUserByUserName(String userName){
        return new User(userName);
    }


    @Override
    public String toString() {
        return "\n\nUser: " + "\nName: " + getFirstName() + "\nSurname: " + getLastName() + "\nUsername: "
                + getUserName() + "\nAmount of Tasks: " + getTaskCounter() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user= (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(taskCounter, user.taskCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,userName,taskCounter);
    }
}

