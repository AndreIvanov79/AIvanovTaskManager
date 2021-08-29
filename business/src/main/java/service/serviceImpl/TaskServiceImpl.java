package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import entity.Task;
import service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
    @Override
    public void serviceCreateInstance(String[] args) {
        String userName=args[1].substring(4);
        String taskTitle=args[2].substring(4);
        String description=args[3].substring(4);

        daoFactory.getTaskDAO(TypeOfFactory.HIBER).createTask(taskTitle,description,userName);

    }
    @Override
    public List<Task> serviceGetListOf(String[] args) {
        String userName=args[1].substring(4);

        List<Task> list=daoFactory.getTaskDAO(TypeOfFactory.HIBER).showUserTasks(userName);
        return list;
    }
}
