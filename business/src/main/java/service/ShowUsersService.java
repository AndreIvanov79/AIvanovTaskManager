package service;

import dao.daoImpl.UserDAOFactory;

import java.sql.SQLException;

public class ShowUsersService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        UserDAOFactory userDAOFactory=new UserDAOFactory();
        userDAOFactory.getListDAO().getList();
    }
}
