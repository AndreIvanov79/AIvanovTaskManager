package service;

import entitiy.Entity;
import entitiy.Task;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    Entity serviceCreateInstance(String[] args);
    List<String> serviceGetListOf(String[] args);
}
