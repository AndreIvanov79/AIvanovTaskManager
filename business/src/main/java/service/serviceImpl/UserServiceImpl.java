package service.serviceImpl;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import entity.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();
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
    @Override
    public List<User> serviceGetListOf(String[] args) {
        List<User> list=daoFactory.getUserDAO(TypeOfFactory.HIBER).showAllUsers();
        return list;
    }

    public void serviceCreateUserAndAssignTask(String[] args){
        String firstName=args[1].substring(4);
        String lastName=args[2].substring(4);
        String userName=args[3].substring(4);
        String taskTitle=args[4].substring(4);
        String description=args[5].substring(4);

        daoFactory.getUserDAO(TypeOfFactory.HIBER).createUserAndAssignTask(firstName,lastName,userName,taskTitle,description);
    }
}
