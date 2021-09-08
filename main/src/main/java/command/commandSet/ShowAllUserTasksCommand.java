package command.commandSet;

import command.Command;
import service.serviceImpl.TaskServiceImpl;

public class ShowAllUserTasksCommand implements Command {
    TaskServiceImpl taskDAOService=new TaskServiceImpl();
    @Override
    public void execute(String[] args){
        String userName=args[1].substring(4);
        taskDAOService.serviceGetListOf(userName);
    }
}
