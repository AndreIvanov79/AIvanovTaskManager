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
        if (args[0].equals(CREATE_USER)){
            CreateUserCommand createUserCommand=new CreateUserCommand();
            createUserCommand.executeCommand(args);
        }
        if (args[0].equals(SHOW_ALL_USERS)){
            ShowUsersCommand showUsersCommand= new ShowUsersCommand();
            showUsersCommand.executeCommand(args);
        }
        if (args[0].equals(ADD_TASK)){
            AddTaskCommand addTaskCommand=new AddTaskCommand();
            addTaskCommand.executeCommand(args);
        }
        if (args[0].equals(SHOW_TASKS)){
            ShowAllUserTasksCommand showAllUserTasksCommand=new ShowAllUserTasksCommand();
            showAllUserTasksCommand.executeCommand(args);
        }
    }
}
