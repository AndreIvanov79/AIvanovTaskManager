package service;

import dao.daoImpl.DAOFactoryImpl;
import dao.daoImpl.TypeOfFactory;
import entity.User;

import java.util.List;

public class UserService {
    DAOFactoryImpl daoFactory=new DAOFactoryImpl();

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

    public List<User> serviceGetListOf(String[] args) {
        List<User> list=daoFactory.getUserDAO(TypeOfFactory.HIBER).showAllUsers();
        return list;
    }
}
