package command.commandSet;

import command.Command;
import service.UserService;

public class CreateUserCommand implements Command {
    UserService userDAOService=new UserService();
    @Override
    public void execute(String[] args)  {
        userDAOService.serviceCreateInstance(args);
    }
}
