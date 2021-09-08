package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

public class CreateUserAndAssignTaskCommand implements Command {
    UserServiceImpl userService=new UserServiceImpl();

    @Override
    public void execute(String[] args) {
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);
        String taskTitle=args[4].substring(4);
        String description=args[5].substring(4);

       userService.serviceCreateUserAndAssignTask(firstName,lastName,userName,taskTitle,description);
    }
}
