package command.commandSet;

import command.Command;
import service.TaskService;

public class ShowAllUserTasksCommand implements Command {
    TaskService taskDAOService=new TaskService();
    @Override
    public void execute(String[] args){
        taskDAOService.serviceGetListOf(args);
    }
}
