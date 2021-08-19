package dao;

public interface DAOFactory {
    CreateDAO getCreateDAO();
    GetListDAO getListDAO();
    GetSBsListDAO getListOf();
}
