package service;

import dao.daoImpl.TaskDAOFactory;

import java.sql.SQLException;

public class AddTaskService implements ServiceDAO {

    @Override
    public void service(String[] args) throws SQLException{
        TaskDAOFactory taskDAOFactory=new TaskDAOFactory();
            taskDAOFactory.getCreateDAO().create(args[1].substring(4), args[2].substring(4), args[3].substring(4));
    }
}
