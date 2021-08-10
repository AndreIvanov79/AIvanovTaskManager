package com.stefanini.taskmanager.db.daoImpl;

import com.stefanini.taskmanager.entities.Task;
import com.stefanini.taskmanager.utilities.DBConnection;
import org.apache.log4j.Logger;

import java.sql.*;

public class DAOTask {
    private static final Logger log = Logger.getLogger(DAOTask.class);
    public void insertTask(Task task) {
        String taskTitle = task.getTaskTitle();
        long taskID=0;
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "INSERT INTO tasks (task_title) VALUES(?)";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskTitle);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                taskID = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            log.error(e);
            /*if (Objects.nonNull(connection)) {
                try {
                    log.info("Transaction rollback");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }*/
        }
        log.info("Task was inserted");
    }

    public void deleteTaskByTitle(String taskTitle) {
        Connection dbConnection=null;
        PreparedStatement statement;

        try {
            // Execute a query
            String sql = "DELETE  FROM tasks WHERE task_title = ?";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,taskTitle );
            statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT *  FROM tasks;");
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("task_id"));
                System.out.print(", Title: " + rs.getString("task_title"));
                System.out.print(", user_name: " + rs.getInt("user_name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Task was deleted");
    }


    public void deleteTaskByID(long taskID) {
        PreparedStatement statement;
        Connection dbConnection=null;

        try {
            String sql = "DELETE FROM tasks WHERE task_id=?";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setLong(1,taskID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Task was deleted");
    }

    public void updateTask(Task task, String newTitle) {
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "UPDATE tasks SET task_title=? WHERE task_title=?";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,newTitle);
            statement.setString(2,task.getTaskTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Task was updated");
    }

    public void deleteAllTasks(){
        Connection dbConnection = null;
        PreparedStatement statement;

        try {
            String sql = "DELETE FROM tasks;";
            dbConnection = DBConnection.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e){
            log.error(e.getMessage());
        }
        log.info("Tasks table is empty");
    }

}
