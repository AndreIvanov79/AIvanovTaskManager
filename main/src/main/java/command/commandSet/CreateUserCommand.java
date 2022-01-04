package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

import java.util.List;

public class CreateUserCommand implements Command {
    UserServiceImpl userDAOService=new UserServiceImpl();
    @Override
    public void execute(List<String> param)  {
        String firstName=param.get(0);
        String lastName=param.get(1);
        String userName=param.get(2);

        userDAOService.serviceCreateInstance(firstName,lastName,userName);
    }
}
