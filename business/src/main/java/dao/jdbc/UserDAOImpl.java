package dao.jdbc;

import dao.inter.UserDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);
    @Override
    public void createUser(String firstName, String lastName, String userName) {
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
    public List<User> showAllUsers() {
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<User> usersList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM users ;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                User user=new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserName(resultSet.getString("user_name"));
                usersList.add(user);
            }
            LOG.info("List of existing users: ");
            for (User user: usersList){
                LOG.info(user.toString());
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return usersList;
    }

    @Override
    public User createUserAndAssignTask(String firstName, String lastName, String userName, String taskTitle, String description) {
        User user=new User(firstName,lastName,userName);
        Task task=new Task(taskTitle,description);
        user.addTaskToList(task);
        return user;
    }
}
