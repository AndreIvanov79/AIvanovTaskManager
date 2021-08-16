package dao.daoImpl;

import dao.TaskDAO;
import utilities.Connector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    private static final Logger LOG = Logger.getLogger(TaskDAOImpl.class);

    @Override
    public void addTaskToUser(String userName, String taskTitle) {
        long taskID=0;
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "INSERT INTO tasks (task_title,user_name) VALUES(?,?)";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskTitle);
            statement.setString(2, userName);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                taskID = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        LOG.info("Task was inserted");
    }

   @Override
    public List<String> showAllUserTasks(String userName){
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<String> tasksList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks WHERE user_name=?;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,userName);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                tasksList.add(rs.getString("task_title"));
            };
            for (String str: tasksList) {
                LOG.info(str);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        LOG.info("Tasks presents");
        return tasksList;
    }

    public void deleteAllTasks(){
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "DELETE FROM tasks;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        LOG.info("Tasks table is empty");
    }
}

