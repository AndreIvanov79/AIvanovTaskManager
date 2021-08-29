
import command.CommandExecutor;
import entity.User;
import org.apache.maven.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
         CommandExecutor command=new CommandExecutor();
         command.execute(args);
    }
}
