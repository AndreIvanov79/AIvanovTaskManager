package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

    void createUser(String firstName, String lastName, String userName) throws SQLException;

    ArrayList<String> showAllUsers() throws SQLException;

}
