package command;

import service.AddTaskService;

import java.sql.SQLException;

public class AddTaskCommand implements CommandExecution {
    AddTaskService addTaskService =new AddTaskService();
    @Override
    public void executeCommand(String[] args){
        try {
            addTaskService.service(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
