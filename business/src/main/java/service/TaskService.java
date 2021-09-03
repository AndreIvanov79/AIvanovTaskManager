package service;

import entity.Task;

/**
 * Makes a contract for realization commands for translates them in the DataBase queries.
 */

import java.util.List;

public interface TaskService {

    /** Method invokes DAO layer and transfers arguments for build Database query.
     * @param args necessary arguments for create the User-instance and DataBase query.
     */
    Task serviceCreateInstance(String[] args);

    /**
     * Method invokes DAO layer for create DataBase query
     * @param args necessary arguments for create the Users list of tasks and DataBase query.
     * @return result-list of Tasks of marked user-instance from Database
     */
    List<Task> serviceGetListOf(String[] args);
}
