package com.stefanini.taskmanager;


import Services.ExecutionQuery;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {

        ExecutionQuery executionQuery=new ExecutionQuery();
        executionQuery.getExecution(args);

    }

}
