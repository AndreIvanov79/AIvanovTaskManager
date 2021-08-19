package dao.daoImpl;

import dao.GetSBsListDAO;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetListOfTasksDAO implements GetSBsListDAO {
    private static final Logger LOG = Logger.getLogger(GetListOfTasksDAO.class);
    @Override
    public List<String> getList(String userName) {
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<String> tasksList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks WHERE user_name=?;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            statement.setString(1,userName);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                tasksList.add(resultSet.getString("description"));
            };
            for (String str: tasksList) {
                LOG.info(userName+" has task: "+str);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return tasksList;
    }
}
