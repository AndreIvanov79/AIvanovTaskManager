package dao.daoImpl;

import dao.inter.DAOFactory;
import dao.inter.TaskDAO;
import dao.inter.UserDAO;
import dao.hibernate.HiberTaskDAOImpl;
import dao.hibernate.HiberUserDAOImpl;
import dao.jdbc.*;
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
