package service;

import dao.daoImpl.DAOFactoryImpl;
import entitiy.Task;

import java.util.List;

public class TaskService implements Service {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
    @Override
    public Task serviceCreateInstance(String[] args) {
        String userName=args[1].substring(4);
        String taskTitle=args[2].substring(4);
        String description=args[3].substring(4);

        Task task=new Task();
        task.setTaskTitle(taskTitle);
        task.setDescription(description);
        task.setUserName(userName);

        daoFactory.getTaskDAO().createTask(taskTitle,description,userName);
        return task;
    }

    @Override
    public List<String> serviceGetListOf(String[] args) {
        String userName=args[1].substring(4);

        List<String> list=daoFactory.getTaskDAO().showUserTasks(userName);
        daoFactory.getTaskDAO().showUserTasks(userName);
        return list;
    }
}
