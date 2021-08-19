package dao.daoImpl;

import dao.TaskDAO;
import entitiy.Task;
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
            String sql = "INSERT INTO tasks (task_title,description,user_name) VALUES(?,?,?)";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskTitle);
            statement.setString(2, description);
            statement.setString(3, userName);
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
    public List<String> showUserTasks(String userName) {
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<String> tasksList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks WHERE user_name=?;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,userName);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                tasksList.add(resultSet.getString("description"));
            };
            for (String str: tasksList) {
                LOG.info(userName+" has task: "+str);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return tasksList;
    }
}
