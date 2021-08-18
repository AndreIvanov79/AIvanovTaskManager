import command.Command;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
        Command command=new Command();
        command.execute(args);
    }

}
