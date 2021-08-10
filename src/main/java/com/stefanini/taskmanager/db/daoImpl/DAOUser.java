package com.stefanini.taskmanager.db.daoImpl;

import com.stefanini.taskmanager.entities.User;
import com.stefanini.taskmanager.utilities.DBConnection;
import org.apache.log4j.Logger;

import java.sql.*;

public class DAOUser {
    private static final Logger log = Logger.getLogger(DAOUser.class);

    public void insertUser(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userName = user.getUserName();
        // User existanceUser= user.getUserByUserName(userName);
        long userID = 0;
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "INSERT INTO users (first_name,last_name,user_name) VALUES(?,?,?)";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, userName);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userID = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            log.error(e);
            /*if (Objects.nonNull(connection)) {
                try {
                    log.info("Transaction rollback");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }*/
        }
        log.info("User was inserted");
    }



    public void deleteUserByUserName(String userName) {
        Connection dbConnection=null;
        PreparedStatement statement;

        try {String sql = "DELETE FROM users WHERE user_name=?";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,userName );
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("User was deleted");
    }


    public void deleteUserByID(long userID) {
        PreparedStatement statement;
        Connection dbConnection=null;

        try {String sql = "DELETE FROM tasks WHERE task_id=?";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setLong(1,userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("User was deleted");
    }

    public void updateUser(User user, String param, String newValue) {
        String userName=user.getUserName();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql=null;
            switch (param){
                case "firstName":
                    sql="UPDATE users SET first_name=? WHERE user_name=?;";
                    break;
                case "lastName":
                    sql="UPDATE users SET last_name=? WHERE user_name=?;";
                    break;
                case "userName":
                    sql="UPDATE users SET user_name=? WHERE user_name=?;";
                    break;
            }
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,newValue);
            statement.setString(2,user.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("User`s data was updated");
    }

    public void deleteAllUsers(){
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "DELETE FROM users;";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e){
            log.error(e.getMessage());
        }
        log.info("Users table is empty");
    }

}


