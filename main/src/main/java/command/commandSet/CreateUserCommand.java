package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

public class CreateUserCommand implements Command {
    UserServiceImpl userDAOService=new UserServiceImpl();
    @Override
    public void execute(String[] args)  {
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);

        userDAOService.serviceCreateInstance(firstName,lastName,userName);
    }
}
