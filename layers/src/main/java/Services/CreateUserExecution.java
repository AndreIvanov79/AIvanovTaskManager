package Services;

import dao.daoImpl.UserDaoImpl;
import utilities.Parser;

import java.sql.SQLException;
import java.util.Map;

public class CreateUserExecution implements Execution {
    @Override
    public void execute(String[] args) throws SQLException {
        UserDaoImpl userDao=new UserDaoImpl();
        Parser parser=new Parser();
        Map<String,String> map=parser.parseFromTerminal(args);
        userDao.createUser(map.get("fn"),map.get("ln"),map.get("un"));
    }
}
