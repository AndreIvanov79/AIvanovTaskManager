package service;

import dao.daoImpl.UserDAOFactory;

import java.sql.SQLException;

public class CreateUserService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        UserDAOFactory userDAOFactory=new UserDAOFactory();
        userDAOFactory.getCreateDAO().create(args[1].substring(4),args[2].substring(4),args[3].substring(4));
    }
}
