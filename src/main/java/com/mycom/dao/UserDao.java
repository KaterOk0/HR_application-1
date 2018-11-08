package com.mycom.dao;

import com.mycom.entity.User;

import java.util.List;

public interface UserDao {
    String SQL_FIND_ALL_USERS = SqlDao.SQL_FIND_ALL + User.TABLE_NAME;
    String SQL_SORT_BY_NAME = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " order by " + User.NAME_COLUMN;
    String SQL_SORT_BY_SURNAME = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " order by " + User.SURNAME_COLUMN;
    String SQL_SORT_BY_EMAIL = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " order by " + User.EMAIL_COLUMN;

    String SQL_FIND_BY_NAME = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " where " + User.NAME_COLUMN + "=?";
    String SQL_FIND_BY_ROLE = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " where " + User.ROLE_COLUMN + "=?";
    String SQL_FIND_BY_ID = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
            " where " + User.ID_COLUMN + "=?";

//    String SQL_SEARCH_BY_NAME = SqlDao.SQL_FIND_ALL + User.TABLE_NAME +
//            " where " + User.NAME_COLUMN + "=?";

    String SQL_INSERT_USER = SqlDao.SQL_INSERT + User.TABLE_NAME +
            "(" + User.NAME_COLUMN + "," + User.SURNAME_COLUMN + "," +
            User.EMAIL_COLUMN + "," + User.PASSWORD_COLUMN + "," +
            User.ROLE_COLUMN + ") values(?,?,?,?,?)";
    String SQL_UPDATE_USER = SqlDao.SQL_UPDATE + User.TABLE_NAME + " set " +
            User.NAME_COLUMN + "=?, " + User.SURNAME_COLUMN + "=?, " +
            User.EMAIL_COLUMN + "=?, " + User.PASSWORD_COLUMN + "=?, " +
            User.ROLE_COLUMN + "=? where " + User.ID_COLUMN + "=?";
    String SQL_DELETE_USER = SqlDao.SQL_DELETE + User.TABLE_NAME + " where " + User.ID_COLUMN + "=?";

    List<User> findAll();
    List<User> findAllSortName();
    List<User> findAllSortNameReverse();
    List<User> findAllSortSurName();
    List<User> findAllSortEmail();
    List<User> findByName(String name);
    List<User> findByRole(String role);

//    List<User> searchByName();

    User findById(long id);

    void insert(User user);

    void update(User user);

    void delete(long userId);

}
