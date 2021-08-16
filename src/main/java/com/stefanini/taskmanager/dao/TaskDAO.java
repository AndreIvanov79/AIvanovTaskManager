package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    List<String> showAllUserTasks(String userName) throws SQLException;

    void addTaskToUser(String userName, String taskTitle) throws SQLException;

}
