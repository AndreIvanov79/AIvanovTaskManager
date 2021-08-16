package com.stefanini.taskmanager.Services;

import com.stefanini.taskmanager.utilities.Parser;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Map;

public class ExecutionQuery {
    private static final Logger LOG = Logger.getLogger(ExecutionQuery.class);

    public void getExecution(String[] args) throws SQLException {
        Parser parser=new Parser();
        Map<String,String> map=parser.parseFromTerminal(args);

        switch (args[0]){
            case "-createUser":
                new CreateUserExecution().execute(args);
                break;
            case "-showAllUsers":
                new ShowUsersExecution().execute(args);
                break;
            case "-addTask":
                new AddTaskExecution().execute(args);
                break;
            case "-showTasks":
                new ShowUserTasksExecution().execute(args);
                break;
            default:LOG.error("Incorrect arguments");
        }
    }
}