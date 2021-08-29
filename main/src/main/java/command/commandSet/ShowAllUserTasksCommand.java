package command.commandSet;

import command.Command;
import service.serviceImpl.TaskServiceImpl;

public class ShowAllUserTasksCommand implements Command {
    TaskServiceImpl taskDAOService=new TaskServiceImpl();
    @Override
    public void execute(String[] args){
        taskDAOService.serviceGetListOf(args);
    }
}
