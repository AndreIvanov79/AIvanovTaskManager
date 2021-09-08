package mythread;

import annotation.Loggable;
import dao.hibernate.HiberUserDAOImpl;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import service.serviceImpl.TaskServiceImpl;
import service.serviceImpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadRunner {
    private static final Logger LOG = Logger.getLogger(ThreadRunner.class);
    public void runner(ArrayList<String> list) {

        UserServiceImpl userService=new UserServiceImpl();
        TaskServiceImpl taskService=new TaskServiceImpl();

       //  ExecutorService executor = Executors.newFixedThreadPool(4);

       ExecutorService executor =
                new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        Callable<User> callableTask1 = () -> {
            System.out.println("The First Thread begins...");
            TimeUnit.MILLISECONDS.sleep(300);
            User user=userService.serviceCreateInstance(list.get(0),list.get(1),list.get(2));
            System.out.println("The First Thread finished");
            return user;
        };
        Callable<Task> callableTask2 = () -> {
            System.out.println("The Second Thread begins...");
            TimeUnit.MILLISECONDS.sleep(300);
            Task task=taskService.serviceCreateInstance(list.get(3),list.get(4),list.get(2));
            System.out.println("The Second Thread finished.");
            return task;
        };
        Callable<List<User>> callableTask3 = () -> {
            System.out.println("The Third Thread begins...");
            TimeUnit.MILLISECONDS.sleep(300);
            List<User> users=userService.serviceGetListOf();
            System.out.println("The Third Thread finished");
            return users;
        };
        Callable<List<Task>> callableTask4 = () -> {
            System.out.println("The Fourth Thread begins...");
            TimeUnit.MILLISECONDS.sleep(300);
            List<Task> tasks=taskService.serviceGetListOf(list.get(2));
            System.out.println("The Fourth Thread finished.");
            return tasks;
        };

        try {
            Future<User> future1 = executor.submit(callableTask1);
            User res1 = future1.get();
            Future<Task> future2 = executor.submit(callableTask2);
            Task res2 = future2.get();
            Future<List<User>> future3 = executor.submit(callableTask3);
            List<User> res3 = future3.get();
            Future<List<Task>> future4 = executor.submit(callableTask4);
            List<Task> res4 = future4.get();
        }catch (ExecutionException | InterruptedException e){
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
