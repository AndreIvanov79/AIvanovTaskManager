package service;

import dao.daoImpl.DaoFactory;
import dao.daoImpl.TaskDAOImpl;

import java.sql.SQLException;

public class ShowUserTasksService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        DaoFactory.getTaskDAO().showAllUserTasks(args[1].substring(4));
    }
}
