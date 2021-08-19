package dao;

public interface DAOFactory {
    UserDAO getUserDAO();
    TaskDAO getTaskDAO();
}
