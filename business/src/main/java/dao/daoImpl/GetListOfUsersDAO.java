package dao.daoImpl;

import dao.GetListDAO;
import org.apache.log4j.Logger;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetListOfUsersDAO implements GetListDAO {
    private static final Logger LOG = Logger.getLogger(GetListOfUsersDAO.class);
    @Override
    public List<String> getList() {
        Connection dbConnection = null;
        PreparedStatement statement;
        ArrayList<String> usersList=new ArrayList<>();
        try {
            String sql = "SELECT user_name FROM users ;";
            dbConnection = Connector.getInstance().getConnection();
            statement = dbConnection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                usersList.add(resultSet.getString("user_name"));
            }
            LOG.info("List of existing users: ");
            for (String str: usersList){
                LOG.info(str);
            }
            resultSet.close();
            statement.close();
            dbConnection.close();
        } catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return usersList;
    }
}
