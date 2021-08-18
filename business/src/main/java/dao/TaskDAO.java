package dao;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void addTaskToUser(String userName, String taskTitle, String description) throws SQLException;

    List<String> showAllUserTasks(String userName);
}
