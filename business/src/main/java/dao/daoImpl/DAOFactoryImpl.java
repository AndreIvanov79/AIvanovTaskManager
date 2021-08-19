package dao.daoImpl;

import dao.DAOFactory;
import dao.TaskDAO;
import dao.UserDAO;

public class DAOFactoryImpl implements DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public TaskDAO getTaskDAO() {
        return new TaskDAOImpl();
    }
}
