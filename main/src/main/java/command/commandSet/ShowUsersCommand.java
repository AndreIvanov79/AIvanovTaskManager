package command.commandSet;

import command.Command;
import service.TaskDAOService;
import service.UserDAOService;

import java.sql.SQLException;

public class ShowUsersCommand implements Command {
    UserDAOService userDAOService=new UserDAOService();
    @Override
    public void execute(String[] args){
        try {
            userDAOService.serviceAddListQuery(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
