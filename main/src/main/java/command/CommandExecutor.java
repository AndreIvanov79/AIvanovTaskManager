package command;

import command.commandSet.*;
import org.apache.log4j.Logger;
import util.Parser;

import java.sql.SQLException;

public class CommandExecutor {
    private static final Logger LOG = Logger.getLogger(CommandExecutor.class);

    public static final String CREATE_USER = "-createUser";
    public static final String SHOW_ALL_USERS = "-showAllUsers";
    public static final String ADD_TASK = "-addTask";
    public static final String SHOW_TASKS = "-showTasks";
    public static final String CREATE_USER_AND_ASSIGN_TASK = "-createUserAndAssignTask";

    public void execute(String[] args) throws SQLException {
        String commandFlag = args[0];
        Parser parser=new Parser();

        switch (commandFlag) {
            case CREATE_USER:
                CreateUserCommand createUserCommand = new CreateUserCommand();
                createUserCommand.execute(parser.parse(args));
                break;
            case SHOW_ALL_USERS:
                ShowUsersCommand showUsersCommand = new ShowUsersCommand();
                showUsersCommand.execute(parser.parse(args));
                break;
            case ADD_TASK:
                AddTaskCommand addTaskCommand = new AddTaskCommand();
                addTaskCommand.execute(parser.parse(args));
                break;
            case SHOW_TASKS:
                ShowAllUserTasksCommand showAllUserTasksCommand = new ShowAllUserTasksCommand();
                showAllUserTasksCommand.execute(parser.parse(args));
                break;
            case CREATE_USER_AND_ASSIGN_TASK:
                CreateUserAndAssignTaskCommand createUserAndAssignTaskCommand = new CreateUserAndAssignTaskCommand();
                createUserAndAssignTaskCommand.execute(parser.parse(args));
                break;
            default:
                LOG.error("Invalid arguments");
        }
    }
}
