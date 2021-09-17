package mythread;

import command.CommandExecutor;
import command.commandSet.AddTaskCommand;
import command.commandSet.CreateUserCommand;
import command.commandSet.ShowAllUserTasksCommand;
import command.commandSet.ShowUsersCommand;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RunnableRanner {
    private static final Logger LOG = Logger.getLogger(RunnableRanner.class);
    CommandExecutor commandExecutor = new CommandExecutor();

    public void runner(List<String> list) {

        List<String> createUserArgs=new ArrayList<>();
        createUserArgs.add(list.get(0));createUserArgs.add(list.get(1));createUserArgs.add(list.get(2));

        List<String> createTaskArgs = new ArrayList<>();
        createTaskArgs.add(list.get(2)); createTaskArgs.add(list.get(3));createTaskArgs.add(list.get(4));

        List<String> showUserTasksArgs = new ArrayList<>();
        showUserTasksArgs.add(list.get(2));

        List<String> showUsersArgs =new ArrayList<>();

        ExecutorService executor =
                new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>());

        Runnable runnableTask1 = () -> {
            System.out.println("The First Thread begins...");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                CreateUserCommand command=new CreateUserCommand();
                command.execute(createUserArgs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The First Thread finished");
        };

        Runnable runnableTask2 = () -> {
            System.out.println("The Second Thread begins...");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                AddTaskCommand command=new AddTaskCommand();
                command.execute(createTaskArgs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The Second Thread finished");
        };

        Runnable runnableTask3 = () -> {
            System.out.println("The Third Thread begins...");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                ShowUsersCommand command=new ShowUsersCommand();
                command.execute(showUsersArgs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The Third Thread finished");
        };

        Runnable runnableTask4 = () -> {
            System.out.println("The Fourth Thread begins...");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                ShowAllUserTasksCommand command=new ShowAllUserTasksCommand();
                command.execute(showUserTasksArgs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The Fourth Thread finished");
        };

        try {
            executor.submit(runnableTask1).get();
            executor.submit(runnableTask2).get();
            executor.submit(runnableTask3);
            executor.submit(runnableTask4);
        } catch (ExecutionException | InterruptedException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

}
