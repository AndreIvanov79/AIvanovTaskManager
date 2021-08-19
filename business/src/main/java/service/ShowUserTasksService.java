package service;

import dao.daoImpl.TaskDAOFactory;

import java.sql.SQLException;

public class ShowUserTasksService implements ServiceDAO {
    @Override
    public void service(String[] args) throws SQLException {
        TaskDAOFactory taskDAOFactory=new TaskDAOFactory();
        taskDAOFactory.getListOf().getList(args[1].substring(4));
    }
}
