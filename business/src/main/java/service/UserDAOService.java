package service;

import dao.DAOFactory;
import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.UserDAOImpl;

import java.sql.SQLException;

public class UserDAOService implements ServiceDAO {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
    @Override
    public void serviceCreateQuery(String[] args) throws SQLException {
        daoFactory.getUserDAO().createUser(args[1].substring(4),args[2].substring(4),args[3].substring(4));
    }

    @Override
    public void serviceAddListQuery(String[] args) throws SQLException {
        daoFactory.getUserDAO().showAllUsers();
    }
}
