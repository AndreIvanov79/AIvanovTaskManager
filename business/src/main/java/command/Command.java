package command;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Command {
    private static final Logger LOG = Logger.getLogger(Command.class);

    public static final String CREATE_USER="-createUser";
    public static final String SHOW_ALL_USERS="-showAllUsers";
    public static final String ADD_TASK="-addTask";
    public static final String SHOW_TASKS="-showTasks";

    public void execute(String[] args) throws SQLException {
        switch (args[0]){
            case CREATE_USER:
                CreateUserCommand createUserCommand=new CreateUserCommand();
                createUserCommand.executeCommand(args);
                break;
            case SHOW_ALL_USERS:
                ShowUsersCommand showUsersCommand= new ShowUsersCommand();
                showUsersCommand.executeCommand(args);
                break;
            case ADD_TASK:
                AddTaskCommand addTaskCommand=new AddTaskCommand();
                addTaskCommand.executeCommand(args);
                break;
            case SHOW_TASKS:
                ShowAllUserTasksCommand showAllUserTasksCommand=new ShowAllUserTasksCommand();
                showAllUserTasksCommand.executeCommand(args);
                break;
            default:
                LOG.error("Invalid arguments");
        }
    }
}
