package dao.daoImpl;

public class DaoFactory {
    public static UserDaoImpl getUserDAO(){
       return UserDaoImpl.createUserDaoImpl();
    }

    public static TaskDAOImpl getTaskDAO(){
        return TaskDAOImpl.createTaskDAOImpl();
    }
}
