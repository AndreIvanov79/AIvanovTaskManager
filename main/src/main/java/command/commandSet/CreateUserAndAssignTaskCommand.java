package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

import java.util.List;

public class CreateUserAndAssignTaskCommand implements Command {
    UserServiceImpl userService=new UserServiceImpl();

    @Override
    public void execute(List<String> param) {
        String firstName=param.get(0);
        String lastName=param.get(1);
        String userName=param.get(2);
        String taskTitle=param.get(3);
        String description=param.get(4);

       userService.serviceCreateUserAndAssignTask(firstName,lastName,userName,taskTitle,description);
    }
}
