package command;

import service.CreateUserService;

import java.sql.SQLException;

public class CreateUserCommand implements CommandExecution {
    CreateUserService createUserService =new CreateUserService();

    @Override
    public void executeCommand(String[] args)  {
        try {
            createUserService.service(args);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
