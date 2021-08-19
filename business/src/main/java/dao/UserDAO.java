package dao;

import java.util.List;

public interface UserDAO {
    void createUser(String firstName,String lastName,String userName);
    List<String> showAllUsers();
}
