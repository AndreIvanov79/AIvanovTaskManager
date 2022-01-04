package dao;

import entity.User;

import java.util.List;

/**
 * Makes a contract for realization of adding data to DataBase and getting data from DataBase in the Class.
 */

public interface UserDAO {
    /**
     * Method transfers three String parameters to DataBase,creates the recording in the Table
     * with appropriate fields.
     * @param firstName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     * @param lastName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     * @param userName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     *                 Should be uniqe.
     * @see dao.hibernateDAO.HiberUserDAOImpl
     * @see dao.jdbcDAO.UserDAOImpl
     */
    void createUser(String firstName,String lastName,String userName);

    /**
     * Method generates all recorded Users from Table and transfers it to the caller object.
     * @return the List of UserNames of the recorded Users.
     * @see dao.hibernateDAO.HiberUserDAOImpl
     * @see dao.jdbcDAO.UserDAOImpl
     */
    List<User> showAllUsers();

    /**
     * Method creates new User at the same time new Task and assigns Task to User
     * @param firstName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     * @param lastName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     * @param userName Data which will records in the DataBase in the appropriate field as the field of the Entity.
     * @param taskTitle the Header of the new task which will be inserted.
     * @param description the Name of task which describe its theme.
     * @return new User entity.
     */
    User createUserAndAssignTask(String firstName, String lastName, String userName, String taskTitle, String description);
}
