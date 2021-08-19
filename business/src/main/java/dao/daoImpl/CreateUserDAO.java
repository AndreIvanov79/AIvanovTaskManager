package dao.daoImpl;

import dao.CreateDAO;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.*;

public class CreateUserDAO implements CreateDAO {
    private static final Logger LOG = Logger.getLogger(CreateUserDAO.class);
    @Override
    public void create(String firstName, String lastName, String userName) {
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
}
