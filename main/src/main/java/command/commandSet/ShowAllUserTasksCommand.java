package command.commandSet;

import command.Command;
import service.serviceImpl.TaskServiceImpl;

import java.util.List;

public class ShowAllUserTasksCommand implements Command {
    TaskServiceImpl taskDAOService=new TaskServiceImpl();
    @Override
    public void execute(List<String> param){
        String userName=param.get(0);
        taskDAOService.serviceGetListOf(userName);
    }
}
