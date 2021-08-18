package command;

import service.ShowUserTasksService;
import service.ShowUsersService;

import java.sql.SQLException;

public class ShowUsersCommand implements CommandExecution {
    ShowUsersService showUsersService=new ShowUsersService();

    @Override
    public void executeCommand(String[] args){
        try {
            showUsersService.service(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
