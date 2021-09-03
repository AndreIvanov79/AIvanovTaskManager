package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import dao.daoImpl.UserDAO;
import entity.User;
import service.UserService;
import annotation.Loggable;
import annotation.Notifiable;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
    UserDAO userDAO=daoFactory.getUserDAO(TypeOfFactory.HIBER);

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

        userDAO.createUser(firstName,lastName,userName);
        return user;
    }

    @Loggable
    @Override
    public List<User> serviceGetListOf(String[] args) {
        List<User> list=userDAO.showAllUsers();
        return list;
    }

    @Notifiable
    @Loggable
    @Override
    public User serviceCreateUserAndAssignTask(String[] args){
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);
        String taskTitle=args[4].substring(4);
        String description=args[5].substring(4);
        User user=null;

        user=userDAO.createUserAndAssignTask(firstName,lastName,userName,taskTitle,description);
        return user;
    }
}
