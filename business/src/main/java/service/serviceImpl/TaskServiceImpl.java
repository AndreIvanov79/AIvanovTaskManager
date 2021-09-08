package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import dao.hibernate.HiberUserDAOImpl;
import entity.Task;
import service.TaskService;
import annotation.Loggable;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();

    @Loggable
    @Override
    public Task serviceCreateInstance(String taskTitle, String description, String userName) {
        HiberUserDAOImpl hiberUserDAO=new HiberUserDAOImpl();

        Task task=new Task(taskTitle,description,hiberUserDAO.getUserByUserName(userName));

        daoFactory.getTaskDAO(TypeOfFactory.HIBER).createTask(taskTitle,description,userName);
        return task;
    }

    @Loggable
    @Override
    public List<Task> serviceGetListOf(String userName) {

        List<Task> list=daoFactory.getTaskDAO(TypeOfFactory.HIBER).showUserTasks(userName);
        System.out.println(list);
        return list;
    }
}
