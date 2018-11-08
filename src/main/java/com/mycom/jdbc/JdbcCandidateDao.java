package com.mycom.jdbc;

import com.mycom.dao.CandidateDao;
import com.mycom.entity.Candidate;
import com.mycom.entity.CandidateCompetence;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

@Repository("CandidateDao")
public class JdbcCandidateDao implements CandidateDao {
    private static long id;

    private DataSource dataSource;

    @Autowired
    private JdbcCandidateCompetenceDao jdbcCandidateCompetenceDao;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Candidate creator(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getLong(Candidate.ID_COLUMN));
        candidate.setName(rs.getString(Candidate.NAME_COLUMN));
        candidate.setSurname(rs.getString(Candidate.SURNAME_COLUMN));
        candidate.setSalary(rs.getDouble(Candidate.SALARY_COLUMN));
        candidate.setBirthday(new DateTime(rs.getDate(Candidate.BIRTHDAY_COLUMN)));
        candidate.setCandidateState(rs.getString(Candidate.CANDIDATE_STATE_COLUMN));
        candidate.setSkills(jdbcCandidateCompetenceDao.findSkill(candidate.getId()));
        return candidate;
    }

    private List<Candidate> getList(String sqlQuery) {
        List<Candidate> list = new ArrayList<Candidate>();
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
    public List<Candidate> findAll() {
        return getList(SQL_FIND_ALL_CANDIDATES);
    }

    @Override
    public List<Candidate> sortSalaryCandidate() {
        return getList(SQL_SORT_SALARY);
    }

    @Override
    public List<Candidate> findByState(String state) {
        List<Candidate> list = new ArrayList<Candidate>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATE);
            statement.setString(1, state);
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
    public List<Candidate> sortNameCandidate() {
        return getList(SQL_SORT_NAME);
    }

    @Override
    public void insert(Candidate candidate) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_CANDIDATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getSurname());
            statement.setDouble(3, candidate.getSalary());
            statement.setDate(4, new Date(candidate.getBirthday().getMillis()));
            statement.setString(5, candidate.getCandidateState());
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
        for (String s : candidate.getSkills()) {
            CandidateCompetence candidatecompetence = new CandidateCompetence();
            candidatecompetence.setIdCandidate(id);
            candidatecompetence.setName(s);
            jdbcCandidateCompetenceDao.insert(candidatecompetence);
        }
    }

    @Override
    public void update(Candidate candidate) {
        Connection connection = null;
        jdbcCandidateCompetenceDao.delete(candidate.getId());
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CANDIDATE);
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getSurname());
            statement.setDouble(3, candidate.getSalary());
            statement.setDate(4, new Date(candidate.getBirthday().getMillis()));
            statement.setString(5, candidate.getCandidateState());
            statement.setLong(6, candidate.getId());
            statement.execute();
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
        for (String s : candidate.getSkills()) {
            CandidateCompetence candidatecompetence = new CandidateCompetence();
            candidatecompetence.setIdCandidate(candidate.getId());
            candidatecompetence.setName(s);
            jdbcCandidateCompetenceDao.insert(candidatecompetence);
        }

    }

    @Override
    public void delete(long candidateId) {
        jdbcCandidateCompetenceDao.delete(candidateId);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CANDIDATE);
            statement.setLong(1, candidateId);
            statement.execute();
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
    public Candidate findById(long id) {
        Candidate candidate = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                candidate = creator(rs);
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
        return candidate;
    }

}
