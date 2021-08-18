package service;

import dao.daoImpl.DaoFactory;
import dao.daoImpl.UserDaoImpl;

import java.sql.SQLException;

public class ShowUsersService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        DaoFactory.getUserDAO().showAllUsers();
    }
}
