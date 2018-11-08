package com.mycom.jdbc;

import com.mycom.dao.UserDao;
import com.mycom.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("UserDao")
public class JdbcUserDao implements UserDao {

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_FIND_ALL_USERS);
        return list;
    }

    @Override
    public void insert(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setLong(6, user.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(long userId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setLong(1, userId);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAllSortName() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_SORT_BY_NAME);
        return list;
    }

    @Override
    public List<User> findAllSortNameReverse() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_SORT_BY_NAME_REVERSE);
        return list;
    }

    @Override
    public List<User> findAllSortSurName() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_SORT_BY_SURNAME);
        return list;
    }

    @Override
    public List<User> findAllSortEmail() {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_SORT_BY_EMAIL);
        return list;
    }

    private void statementToFind(List<User> list, Connection connection, String sqlSortByName) {
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlSortByName);
            ResultSet rs = statement.executeQuery();
            resultSetToFind(list, rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findByName(String name) {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        statementToFind(list, connection, SQL_FIND_BY_NAME);
        return list;
    }

    @Override
    public List<User> findByRole(String role) {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ROLE);
            statement.setString(1, role);
            ResultSet rs = statement.executeQuery();
            resultSetToFind(list, rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private void resultSetToFind(List<User> list, ResultSet rs) throws SQLException {
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong(User.ID_COLUMN));
            user.setEmail(rs.getString(User.EMAIL_COLUMN));
            user.setName(rs.getString(User.NAME_COLUMN));
            user.setSurname(rs.getString(User.SURNAME_COLUMN));
            user.setPassword(rs.getString(User.PASSWORD_COLUMN));
            user.setRole(rs.getString(User.ROLE_COLUMN));
            list.add(user);
        }
    }

    @Override
    public User findById(long id) {
        User user = new User();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(User.ID_COLUMN));
                user.setEmail(rs.getString(User.EMAIL_COLUMN));
                user.setName(rs.getString(User.NAME_COLUMN));
                user.setSurname(rs.getString(User.SURNAME_COLUMN));
                user.setPassword(rs.getString(User.PASSWORD_COLUMN));
                user.setRole(rs.getString(User.ROLE_COLUMN));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}
