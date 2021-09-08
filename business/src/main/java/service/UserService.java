package service;

import entity.User;

/**
 * Makes a contract for realization commands for translates them in the DataBase queries.
 */

import java.util.List;

public interface UserService {

    /**
     * Method invokes DAO layer and transfers arguments for build Database query.
     * @param firstName necessary arguments for create the User-instance and DataBase query.
     * @param lastName
     * @param userName
     * @return instance for transfer it further.
     */
    User serviceCreateInstance(String firstName,String lastName,String userName);

    /**
     * Method invokes DAO layer for create DataBase query
     *
     * @return result-list of all user-instances from Database
     */
    List<User> serviceGetListOf();

    /**
     *
     * @param
     * @return
     */
    User serviceCreateUserAndAssignTask(String firstName,String lastName,String userName,String taskTitle,String description);
}


