package com.stefanini.taskmanager.dao.daoImpl;

import com.stefanini.taskmanager.dao.UserDao;
import com.stefanini.taskmanager.utilities.Connector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao{
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void createUser(String firstName, String lastName, String userName) throws SQLException {
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
            LOG.info("User was created");
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
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                usersList.add(rs.getString("user_name"));
            }
            for (String str: usersList){
                LOG.info(str);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        LOG.info("Users table presents");
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
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        LOG.info("Users table is empty");
    }
}


