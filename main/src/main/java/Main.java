import mythread.RunnableRanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        //CommandExecutor command=new CommandExecutor();
        //command.execute(args);

        Scanner scanner = new Scanner(System.in);
        List<String> userData = new ArrayList<>();
        System.out.println("Enter the data: \n" + "First Name: ");
        if (scanner.hasNext()) {
            userData.add(scanner.nextLine());
        }
        System.out.println("Last Name: ");
        if (scanner.hasNext()) {
            userData.add(scanner.nextLine());
        }
        System.out.println("User Name: ");
        if (scanner.hasNext()) {
            userData.add(scanner.nextLine());
        }
        System.out.println("Task Title: ");
        if (scanner.hasNext()) {
            userData.add(scanner.nextLine());
        }
        System.out.println("Task Description: ");
        if (scanner.hasNext()) {
            userData.add(scanner.nextLine());
        }

        RunnableRanner runnableRanner = new RunnableRanner();
        runnableRanner.runner(userData);

    }

}
