package command.commandSet;

import command.Command;
import service.serviceImpl.TaskServiceImpl;

public class AddTaskCommand implements Command {
    TaskServiceImpl taskDAOService=new TaskServiceImpl();
    @Override
    public void execute(String[] args){
        String userName=args[1].substring(4);
        String taskTitle=args[2].substring(4);
        String description=args[3].substring(4);
        taskDAOService.serviceCreateInstance(taskTitle,description,userName);
    }
}
