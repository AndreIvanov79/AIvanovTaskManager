package dao.daoImpl;

import dao.DAOFactory;
import dao.TaskDAO;
import dao.UserDAO;
import dao.hibernateDAO.HiberTaskDAOImpl;
import dao.hibernateDAO.HiberUserDAOImpl;
import dao.jdbcDAO.*;
import org.apache.log4j.Logger;

public class DAOFactoryImpl implements DAOFactory {
    private static final Logger LOG = Logger.getLogger(DAOFactoryImpl.class);

    @Override
    public UserDAO getUserDAO(TypeOfFactory param) {
        switch (param) {
            case JDBC:
                return new UserDAOImpl();
            case HIBER:
                return new HiberUserDAOImpl();
            default:
                LOG.error("Invalid argument");
        }
        return null;
    }

    @Override
    public TaskDAO getTaskDAO(TypeOfFactory param) {
        switch (param){
            case JDBC:
                return new TaskDAOImpl();
            case HIBER:
                return new HiberTaskDAOImpl();
            default:
                LOG.error("Invalid argument");
        }
        return null;
    }


}
