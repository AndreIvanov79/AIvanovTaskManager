package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.entities.User;

import java.util.ArrayList;

public interface UserService {
    public boolean addToList(User user);
    public void showAllUsers();
}
