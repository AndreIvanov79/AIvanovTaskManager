package command.commandSet;

import command.Command;
import service.serviceImpl.UserServiceImpl;

public class ShowUsersCommand implements Command {
    UserServiceImpl userDAOService=new UserServiceImpl();
    @Override
    public void execute(String[] args){
        userDAOService.serviceGetListOf();
    }
}
