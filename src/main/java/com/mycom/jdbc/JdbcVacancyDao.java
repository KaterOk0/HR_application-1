package com.mycom.jdbc;

import com.mycom.dao.VacancyDao;
import com.mycom.entity.User;
import com.mycom.entity.Vacancy;
import com.mycom.entity.VacancyRequirence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("VacancyDao")
public class JdbcVacancyDao implements VacancyDao {
    private static long id;

    private DataSource dataSource;

    @Autowired
    private JdbcVacancyRequirenceDao jdbcvacancyrequirementdao;

    @Autowired
    private JdbcUserDao users;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Vacancy creator(ResultSet rs) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(rs.getLong(Vacancy.ID_COLUMN));
        vacancy.setExperienceYearRequire(rs.getDouble(Vacancy.EXPERIENCE_YEAR_REQUIRE_COLUMN));
        vacancy.setPosition(rs.getString(Vacancy.POSITION_COLUMN));
        vacancy.setSalaryTo(rs.getDouble(Vacancy.SALARY_TO_COLUMN));
        vacancy.setSalaryFrom(rs.getDouble(Vacancy.SALARY_FROM_COLUMN));
        vacancy.setIdDeveloper(rs.getLong(Vacancy.ID_DEVELOPER));
        vacancy.setSkills(jdbcvacancyrequirementdao.findById(vacancy.getId()));
        User user = users.findById(vacancy.getIdDeveloper());
        vacancy.setDeveloperName(user.getName() + " " + user.getSurname());
        return vacancy;
    }

    @Override
    public List<Vacancy> findAll() {
        List<Vacancy> list = new ArrayList<Vacancy>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_VACANCY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(creator(rs));
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
        return list;
    }

    private List<Vacancy> getList(String sqlQuery) {
        List<Vacancy> list = new ArrayList<Vacancy>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(creator(rs));
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
        return list;
    }

    @Override
    public List<Vacancy> findByCreate(long idDeveloper) {
        List<Vacancy> list = new ArrayList<Vacancy>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FOR_CREATE);
            statement.setLong(1, idDeveloper);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(creator(rs));
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
        return list;
    }

    @Override
    public void insert(Vacancy vacancy) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_VACANCY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vacancy.getPosition());
            statement.setDouble(2, vacancy.getSalaryTo());
            statement.setDouble(3, vacancy.getSalaryFrom());
            statement.setDouble(4, vacancy.getExperienceYearRequire());
            statement.setLong(5, vacancy.getIdDeveloper());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getLong(1);
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
        for (String s : vacancy.getSkills()) {
            VacancyRequirence vacancyrequirence = new VacancyRequirence();
            vacancyrequirence.setIdVacancy(id);
            vacancyrequirence.setSkill(s);
            jdbcvacancyrequirementdao.insert(vacancyrequirence);

        }
    }

    @Override
    public void update(Vacancy vacancy) {
        jdbcvacancyrequirementdao.delete(vacancy.getId());
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_VACANCY);
            statement.setString(1, vacancy.getPosition());
            statement.setDouble(2, vacancy.getSalaryTo());
            statement.setDouble(3, vacancy.getSalaryFrom());
            statement.setDouble(4, vacancy.getExperienceYearRequire());
            statement.setLong(5, vacancy.getIdDeveloper());
            statement.setLong(6, vacancy.getId());
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
        for (String s : vacancy.getSkills()) {
            VacancyRequirence vacancyrequirence = new VacancyRequirence();
            vacancyrequirence.setIdVacancy(vacancy.getId());
            vacancyrequirence.setSkill(s);
            jdbcvacancyrequirementdao.insert(vacancyrequirence);

        }
    }

    @Override
    public void delete(long vacancyId) {
        jdbcvacancyrequirementdao.delete(vacancyId);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_VACANCY);
            statement.setLong(1, vacancyId);
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
    public Vacancy findById(long id) {
        Vacancy vacancy = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                vacancy = creator(rs);
            }
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
        return vacancy;
    }

    @Override
    public List<Vacancy> sortForExperience() {
        return getList(SQL_SORT_FOR_EXPERIENCE);
    }

    @Override
    public List<Vacancy> sortForSalaryFrom() {
        return getList(SQL_SORT_FOR_SALARY_FROM);
    }

    @Override
    public List<Vacancy> sortForSalaryTo() {
        return getList(SQL_SORT_FOR_SALARY_TO);
    }
}
