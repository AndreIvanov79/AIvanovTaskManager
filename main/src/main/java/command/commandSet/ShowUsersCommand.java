package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

import java.util.List;

public class ShowUsersCommand implements Command {
    UserServiceImpl userDAOService=new UserServiceImpl();
    @Override
    public void execute(List<String> param){
        userDAOService.serviceGetListOf();
    }
}
