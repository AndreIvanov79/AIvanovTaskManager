package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;

public class UserServiceImpl implements UserService {
    private ArrayList<User> listOfUsers;

    private ArrayList<User> getListOfUsers(){
        if (listOfUsers==null){
            listOfUsers=new ArrayList<User>();
        }
        return listOfUsers;
    }

    public boolean addToList(User user){
        getListOfUsers().add(user);
        return true;
    }

    public void showAllUsers(){
        for (User user: listOfUsers){
            System.out.println(user.getUserName()+" "+user.getFirstName()+" "+user.getLastName());
        }
    }
}

