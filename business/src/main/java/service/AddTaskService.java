package service;

import dao.daoImpl.DaoFactory;
import dao.daoImpl.TaskDAOImpl;

import java.sql.SQLException;

public class AddTaskService implements ServiceDAO {

    @Override
    public void service(String[] args) throws SQLException {
        DaoFactory.getTaskDAO().addTaskToUser(args[1].substring(4),args[2].substring(4),args[3].substring(4));
    }
}
