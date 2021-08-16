package Services;

import dao.daoImpl.UserDaoImpl;
import utilities.Parser;

import java.sql.SQLException;
import java.util.Map;

public class ShowUsersExecution implements Execution {
    @Override
    public void execute(String[] args) throws SQLException {
        UserDaoImpl userDao=new UserDaoImpl();
        Parser parser=new Parser();
        Map<String,String> map=parser.parseFromTerminal(args);
        userDao.showAllUsers();
    }
}
