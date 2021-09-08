package dao.inter;

import dao.daoImpl.TypeOfFactory;

/**
 * Make a contract for possibility to initialize instance from where it will be necessary.
 */
public interface DAOFactory {
    /**
     * Create an instance of UserDAO Class for using its fields and methods.
     * @param
     * @return new UserDAO-object to the caller object.
     */
    UserDAO getUserDAO(TypeOfFactory param);

    /**
     * Create an instance of TaskDAO Class for using its fields and methods.
     * @param
     * @return new TaskDAO-object to the caller object.
     */
    TaskDAO getTaskDAO(TypeOfFactory param);
}
