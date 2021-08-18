package service;

import dao.daoImpl.DaoFactory;

import java.sql.SQLException;

public class CreateUserService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        DaoFactory.getUserDAO().createUser(args[1].substring(4),args[2].substring(4),args[3].substring(4));
    }
}
