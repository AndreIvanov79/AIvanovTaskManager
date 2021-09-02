package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import entity.Task;
import entity.User;
import service.UserService;
import service.annotation.Loggable;
import service.annotation.Notifiable;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();

    @Loggable
    @Override
    public User serviceCreateInstance(String[] args) {
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);

        User user=new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        daoFactory.getUserDAO(TypeOfFactory.HIBER).createUser(firstName,lastName,userName);
        return user;
    }

    @Loggable
    @Override
    public List<User> serviceGetListOf(String[] args) {
        List<User> list=daoFactory.getUserDAO(TypeOfFactory.HIBER).showAllUsers();
        return list;
    }

    @Notifiable
    @Loggable
    public User serviceCreateUserAndAssignTask(String[] args){
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);
        String taskTitle=args[4].substring(4);
        String description=args[5].substring(4);

        daoFactory.getUserDAO(TypeOfFactory.HIBER).createUserAndAssignTask(firstName,lastName,userName,taskTitle,description);

        Task task=new Task(taskTitle,description);
        User user=new User(firstName,lastName,userName);
        user.addTaskToList(task);
        System.out.println("For control: "+user);
        return user;
    }
}
