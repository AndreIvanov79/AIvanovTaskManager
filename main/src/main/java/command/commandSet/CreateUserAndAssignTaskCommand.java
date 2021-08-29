package command.commandSet;

import command.Command;
import entity.User;
import service.serviceImpl.TaskServiceImpl;
import service.serviceImpl.UserServiceImpl;

public class CreateUserAndAssignTaskCommand implements Command {
    UserServiceImpl userService=new UserServiceImpl();
    TaskServiceImpl taskService=new TaskServiceImpl();

    @Override
    public void execute(String[] args) {
       userService.serviceCreateUserAndAssignTask(args);
    }
}
