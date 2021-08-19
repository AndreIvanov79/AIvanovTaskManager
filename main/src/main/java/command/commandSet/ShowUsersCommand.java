package command.commandSet;

import command.Command;
import service.UserService;

public class ShowUsersCommand implements Command {
    UserService userDAOService=new UserService();
    @Override
    public void execute(String[] args){
        userDAOService.serviceGetListOf(args);
    }
}
