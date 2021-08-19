package command.commandSet;

import command.Command;
import service.TaskDAOService;

import java.sql.SQLException;

public class AddTaskCommand implements Command {
    TaskDAOService taskDAOService=new TaskDAOService();
    @Override
    public void execute(String[] args){
        try {
            taskDAOService.serviceAddListQuery(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
