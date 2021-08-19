package service;

import java.sql.SQLException;

public interface ServiceDAO {
    void serviceCreateQuery(String[] args) throws SQLException;
    void serviceAddListQuery(String[] args) throws SQLException;
}
