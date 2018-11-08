package com.mycom.jdbc;

import com.mycom.dao.InterviewDao;
import com.mycom.entity.Candidate;
import com.mycom.entity.Interview;
import com.mycom.entity.Vacancy;

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
import java.util.ArrayList;
import java.util.List;

@Repository("InterviewDao")
public class JdbcInterviewDao implements InterviewDao {

    private DataSource dataSource;

    @Autowired
    private JdbcVacancyDao jdbcvacancydao;

    @Autowired
    private JdbcCandidateDao jdbccandidatedao;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Interview creator(ResultSet rs) throws SQLException {
        Interview interview = new Interview();
        interview.setId(rs.getLong(Interview.ID_COLUMN));
        interview.setPlanDate(new DateTime(rs.getDate(Interview.PLAN_DATE_COLUMN)));
        interview.setFactDate(new DateTime(rs.getDate(Interview.FACT_DATE_COLUMN)));
        interview.setIdCandidate(rs.getLong(Interview.ID_CANDIDATE_COLUMN));
        interview.setIdVacancy(rs.getLong(Interview.ID_VACANCY_COLUMN));
        interview.setName(rs.getString(Interview.NAME_COLUMN));
        Candidate candidate = jdbccandidatedao.findById(interview.getIdCandidate());
        Vacancy vacancy = jdbcvacancydao.findById(interview.getIdVacancy());
        interview.setVacancyName(vacancy.getPosition());
        interview.setCandidateName(candidate.getName() + " " + candidate.getSurname());
        return interview;
    }

    private List<Interview> getList(String sqlQuery) {
        List<Interview> list = new ArrayList<Interview>();
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
    public List<Interview> findAll() {
        return getList(SQL_FIND_ALL_INTERVIEW);
    }

    @Override
    public List<Interview> sortByDateFact() {
        return getList(SQL_SORT_BY_DATE_FACT);
    }

    @Override
    public List<Interview> sortByDatePlan() {
        return getList(SQL_SORT_BY_DATE_PLAN);
    }

    @Override
    public List<Interview> findByCandidate(long candidateId) {
        List<Interview> list = new ArrayList<Interview>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CANDIDATE);
            statement.setLong(1, candidateId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(creator(rs));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void insert(Interview interview) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTERVIEW);
            statement.setDate(1, new Date(interview.getPlanDate().getMillis()));
            statement.setDate(2, new Date(interview.getFactDate().getMillis()));
            statement.setLong(3, interview.getIdCandidate());
            statement.setLong(4, interview.getIdVacancy());
            statement.setString(5, interview.getName());
            statement.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Interview interview) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INTERVIEW);
            statement.setDate(1, new Date(interview.getPlanDate().getMillis()));
            statement.setDate(2, new Date(interview.getFactDate().getMillis()));
            statement.setLong(3, interview.getIdCandidate());
            statement.setLong(4, interview.getIdVacancy());
            statement.setString(5, interview.getName());
            statement.setLong(6, interview.getId());
            statement.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(long interviewId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_INTERVIEW);
            statement.setLong(1, interviewId);
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public Interview findById(long id) {
        Interview interview = new Interview();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                interview = creator(rs);
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return interview;
    }

}
