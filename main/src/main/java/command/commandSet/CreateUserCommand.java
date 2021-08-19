package command.commandSet;

import command.Command;
import service.UserDAOService;

import java.sql.SQLException;

public class CreateUserCommand implements Command {
    UserDAOService userDAOService=new UserDAOService();
    @Override
    public void execute(String[] args)  {
        try {
            userDAOService.serviceCreateQuery(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
