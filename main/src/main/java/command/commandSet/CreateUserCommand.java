package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

public class CreateUserCommand implements Command {
    UserServiceImpl userDAOService=new UserServiceImpl();
    @Override
    public void execute(String[] args)  {
        userDAOService.serviceCreateInstance(args);
    }
}
