package com.mycom.jdbc;

import com.mycom.dao.CandidateStateDao;
import com.mycom.entity.CandidateState;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("CandidateStateDao")
public class JdbcCandidateStateDao implements CandidateStateDao {

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static CandidateState creator(ResultSet rs) throws SQLException {
        CandidateState candidatestate = new CandidateState();
        candidatestate.setName(rs.getString(CandidateState.NAME_COLUMN));
        return candidatestate;
    }

    private List<CandidateState> getList(String sqlQuery) {
        List<CandidateState> list = new ArrayList<CandidateState>();
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
    public List<CandidateState> findAll() {
        return getList(SQL_FIND_ALL_STATES);
    }

}
