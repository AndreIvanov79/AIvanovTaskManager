package service;

import dao.daoImpl.DAOFactoryImpl;
import entitiy.User;

import java.util.List;

public class UserService implements Service {
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

        daoFactory.getUserDAO().createUser(firstName,lastName,userName);
        return user;
    }

    @Override
    public List<String> serviceGetListOf(String[] args) {
        List<String> list=daoFactory.getUserDAO().showAllUsers();
        daoFactory.getUserDAO().showAllUsers();
        return list;
    }
}
