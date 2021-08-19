package command.commandSet;

import command.Command;
import service.TaskService;

public class AddTaskCommand implements Command {
    TaskService taskDAOService=new TaskService();
    @Override
    public void execute(String[] args){
        taskDAOService.serviceCreateInstance(args);
    }
}
