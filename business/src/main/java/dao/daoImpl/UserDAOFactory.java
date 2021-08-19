package dao.daoImpl;

import dao.CreateDAO;
import dao.DAOFactory;
import dao.GetListDAO;
import dao.GetSBsListDAO;

public class UserDAOFactory implements DAOFactory {
    @Override
    public CreateDAO getCreateDAO() {
        return new CreateUserDAO();
    }

    @Override
    public GetListDAO getListDAO() {
        return new GetListOfUsersDAO();
    }

    @Override
    public GetSBsListDAO getListOf() {
        return null;
    }
}
