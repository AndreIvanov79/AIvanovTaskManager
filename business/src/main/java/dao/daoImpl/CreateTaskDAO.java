package dao.daoImpl;

import dao.CreateDAO;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.*;

public class CreateTaskDAO implements CreateDAO {
    private static final Logger LOG = Logger.getLogger(CreateTaskDAO.class);
    @Override
    public void create(String userName, String taskTitle, String description) {
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
}
