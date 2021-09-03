package service;

import entity.User;

/**
 * Makes a contract for realization commands for translates them in the DataBase queries.
 */

import java.util.List;

public interface UserService {

    /**
     * Method invokes DAO layer and transfers arguments for build Database query.
     * @param args necessary arguments for create the User-instance and DataBase query.
     * @return instance for transfer it further.
     */
    User serviceCreateInstance(String[] args);

    /**
     * Method invokes DAO layer for create DataBase query
     * @param args
     * @return result-list of all user-instances from Database
     */
    List<User> serviceGetListOf(String[] args);

    /**
     *
     * @param args
     * @return
     */
    User serviceCreateUserAndAssignTask(String[] args);
}


