package dao.daoImpl;

import entity.Task;

import java.util.List;

/**
 * Makes a contract for realization of adding data to DataBase and getting data from DataBase in the Class.
 */

public interface TaskDAO{
    /**
     * Method transfers three String parameters to DataBase,creates the recording in the Table
     * with appropriate fields.
     * @param taskTitle the Header of the new task which will be inserted.
     * @param description the Name of task which describe its theme.
     * @param userName the assigned User. It is also a foreign-key.
     */
    void createTask(String taskTitle,String description,String userName);

    /**
     * Method generates all recorded Tasks of User from Table and transfers it to the caller object.
     * @param userName User whose data collecting this Method.
     * @return List of Titles of Tasks assigned to this User.
     */
    List<Task> showUserTasks(String userName);
}
