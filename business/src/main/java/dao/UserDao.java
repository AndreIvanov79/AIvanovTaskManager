package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

    void createUser(String firstName, String lastName, String userName);

    ArrayList<String> showAllUsers() throws SQLException;

}
