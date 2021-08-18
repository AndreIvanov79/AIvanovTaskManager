package command;

import service.ShowUserTasksService;

import java.sql.SQLException;

public class ShowAllUserTasksCommand implements CommandExecution {
    ShowUserTasksService showUserTasksService =new ShowUserTasksService();
    @Override
    public void executeCommand(String[] args){
        try {
            showUserTasksService.service(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
