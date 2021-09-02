package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import dao.hibernate.HiberUserDAOImpl;
import entity.Task;
import entity.User;
import service.TaskService;
import service.annotation.Loggable;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();

    @Loggable
    @Override
    public Task serviceCreateInstance(String[] args) {
        HiberUserDAOImpl hiberUserDAO=new HiberUserDAOImpl();
        String userName=args[1].substring(4);
        String taskTitle=args[2].substring(4);
        String description=args[3].substring(4);

        Task task=new Task(taskTitle,description,hiberUserDAO.getUserByUserName(userName));

        daoFactory.getTaskDAO(TypeOfFactory.HIBER).createTask(taskTitle,description,userName);
        return task;
    }

    @Loggable
    @Override
    public List<Task> serviceGetListOf(String[] args) {
        String userName=args[1].substring(4);

        List<Task> list=daoFactory.getTaskDAO(TypeOfFactory.HIBER).showUserTasks(userName);
        System.out.println(list);
        return list;
    }
}
