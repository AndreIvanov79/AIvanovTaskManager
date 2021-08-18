package dao.daoImpl;

import dao.UserDao;
import util.Connector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private UserDaoImpl() {
    }

    public static UserDaoImpl createUserDaoImpl() {
        return new UserDaoImpl();
    }

    /**
     * @param firstName
     * @param lastName
     * @param userName
     */
    @Override
    public void createUser(String firstName,String lastName,String userName) {
        long userID = 0;
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "INSERT INTO users (first_name,last_name,user_name) VALUES(?,?,?)";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, userName);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userID = resultSet.getLong(1);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
            LOG.info("User "+userName+" was succesfuly created");
        } catch (SQLException e) {
            LOG.error(e.getMessage());

        }
    }

    @Override
    public ArrayList<String> showAllUsers(){
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<String> usersList=new ArrayList<>();
        try {
            String sql = "SELECT user_name FROM users ;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                usersList.add(resultSet.getString("user_name"));
            }
            LOG.info("List of existing users: ");
            for (String str: usersList){
                LOG.info(str);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return usersList;
    }

    public void deleteAllUsers(){
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "DELETE FROM users;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.executeUpdate();
            LOG.info("Users table is empty");
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
    }
}


