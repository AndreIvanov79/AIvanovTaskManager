package command.commandSet;

import command.Command;
import service.serviceImpl.TaskServiceImpl;

import java.util.List;

public class AddTaskCommand implements Command {
    TaskServiceImpl taskDAOService=new TaskServiceImpl();
    @Override
    public void execute(List<String> param){
        String userName=param.get(0);
        String taskTitle=param.get(1);
        String description=param.get(2);
        taskDAOService.serviceCreateInstance(taskTitle,description,userName);
    }
}
