package com.stefanini.taskmanager.Services;

import com.stefanini.taskmanager.dao.daoImpl.TaskDAOImpl;
import com.stefanini.taskmanager.utilities.Parser;

import java.sql.SQLException;
import java.util.Map;

public class AddTaskExecution implements Execution{

    @Override
    public void execute(String[] args) throws SQLException {
        TaskDAOImpl taskDAO=new TaskDAOImpl();
        Parser parser=new Parser();
        Map<String,String> map=parser.parseFromTerminal(args);
        taskDAO.addTaskToUser(map.get("un"),map.get("tt"));
    }
}
