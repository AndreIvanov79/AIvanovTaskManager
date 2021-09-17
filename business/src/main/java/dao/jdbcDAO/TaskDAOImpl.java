package dao.jdbcDAO;

import dao.TaskDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TaskDAOImpl implements TaskDAO {
    private static final Logger LOG = Logger.getLogger(TaskDAOImpl.class);
    @Override
    public void createTask(String taskTitle, String description, String userName) {
        long taskID=0;
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "INSERT INTO tasks (task_title,description,user_id) VALUES(?,?,?)";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskTitle);
            statement.setString(2, description);
            statement.setLong(3, getUser(userName).getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                taskID = resultSet.getLong(1);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
            LOG.info("Task "+taskTitle+" was inserted");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Task> showUserTasks(String userName) {
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<Task> tasksList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks WHERE user_id=?;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setObject(1,getUser(userName).getUserName());
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Task task=new Task();
                task.setTaskTitle(resultSet.getString("task_title"));
                task.setDescription(resultSet.getString("description"));
               // task.setUserID(resultSet.getInt("user_id"));
                tasksList.add(task);
            };
            for (Task task: tasksList) {
                LOG.info(userName+" has task: "+task.toString());
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return tasksList;
    }

    public User getUser(String userName){
        User user=new User();
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<Task> tasksList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM users WHERE user_name=?;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setFirstName(resultSet.getString("user_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserName(resultSet.getString("user_name"));
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        }catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return user;
    }
}
