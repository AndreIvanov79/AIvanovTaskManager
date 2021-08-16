package com.stefanini.taskmanager.Services;

import java.sql.SQLException;

public interface Execution {

    void execute(String[] args) throws SQLException;
}
