package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import dao.UserDAO;
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
    public User serviceCreateInstance(String firstName,String lastName,String userName) {
        User user=new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        userDAO.createUser(firstName,lastName,userName);
        return user;
    }

    @Loggable
    @Override
    public List<User> serviceGetListOf() {
        List<User> list=userDAO.showAllUsers();
        return list;
    }

    @Notifiable
    @Loggable
    @Override
    public User serviceCreateUserAndAssignTask(String firstName,String lastName,String userName,String taskTitle,String description){

        User user=null;

        user=userDAO.createUserAndAssignTask(firstName,lastName,userName,taskTitle,description);
        return user;
    }
}
