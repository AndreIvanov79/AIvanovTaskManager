package service;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import entity.Task;

import java.util.List;

public class TaskService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();

    public Task serviceCreateInstance(String[] args) {
        String userName=args[1].substring(4);
        String taskTitle=args[2].substring(4);
        String description=args[3].substring(4);

        Task task=new Task();
        task.setTaskTitle(taskTitle);
        task.setDescription(description);
        task.setUserName(userName);

        daoFactory.getTaskDAO(TypeOfFactory.HIBER).createTask(taskTitle,description,userName);
        return task;
    }

    public List<Task> serviceGetListOf(String[] args) {
        String userName=args[1].substring(4);

        List<Task> list=daoFactory.getTaskDAO(TypeOfFactory.HIBER).showUserTasks(userName);
        return list;
    }
}
