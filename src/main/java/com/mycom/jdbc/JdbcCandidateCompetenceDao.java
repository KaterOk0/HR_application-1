package com.mycom.jdbc;

import com.mycom.dao.CandidateCompetenceDao;
import com.mycom.entity.CandidateCompetence;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("CandidateCompetenceDao")
public class JdbcCandidateCompetenceDao implements CandidateCompetenceDao {

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> findSkill(long id) {
        List<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_SKILL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(CandidateCompetence.SKILL_NAME));
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
    public void insert(CandidateCompetence candidatecompetence) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SKILL);
            statement.setLong(1, candidatecompetence.getIdCandidate());
            statement.setString(2, candidatecompetence.getName());
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
    public void delete(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SKILL);
            statement.setLong(1, id);
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
}
