package dao;

import java.util.List;

public interface TaskDAO{
    void createTask(String taskTitle,String description,String userName);
    List<String> showUserTasks(String userName);
}
