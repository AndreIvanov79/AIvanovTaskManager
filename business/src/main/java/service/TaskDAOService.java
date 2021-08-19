package service;

import dao.daoImpl.DAOFactoryImpl;

import java.sql.SQLException;

public class TaskDAOService implements ServiceDAO {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
    @Override
    public void serviceCreateQuery(String[] args) throws SQLException {
        daoFactory.getTaskDAO().createTask(args[1].substring(4),args[2].substring(4),args[3].substring(4));
    }

    @Override
    public void serviceAddListQuery(String[] args) throws SQLException {
        daoFactory.getTaskDAO().showUserTasks(args[1].substring(4));
    }
}
