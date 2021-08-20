package dao;

/**
 * Make a contract for possibility to initialize instance from where it will be necessary.
 */
public interface DAOFactory {
    /**
     * Create an instance of UserDAO Class for using its fields and methods.
     * @return new UserDAO-object to the caller object.
     */
    UserDAO getUserDAO();

    /**
     * Create an instance of TaskDAO Class for using its fields and methods.
     * @return new TaskDAO-object to the caller object.
     */
    TaskDAO getTaskDAO();
}
