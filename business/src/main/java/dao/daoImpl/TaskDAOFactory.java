package dao.daoImpl;

import dao.CreateDAO;
import dao.DAOFactory;
import dao.GetListDAO;
import dao.GetSBsListDAO;

public class TaskDAOFactory implements DAOFactory {
    @Override
    public CreateDAO getCreateDAO() {
        return new CreateTaskDAO();
    }

    @Override
    public GetListDAO getListDAO() {
        return null;
    }

    @Override
    public GetSBsListDAO getListOf() {
        return new GetListOfTasksDAO();
    }
}
