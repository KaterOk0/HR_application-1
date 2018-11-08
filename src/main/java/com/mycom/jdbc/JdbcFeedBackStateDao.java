package com.mycom.jdbc;

import com.mycom.dao.FeedBackStateDao;
import com.mycom.entity.FeedBackState;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("FeedBackStateDao")
public class JdbcFeedBackStateDao implements FeedBackStateDao {

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static FeedBackState creater(ResultSet rs) throws SQLException {
        FeedBackState feedbackstate = new FeedBackState();
        feedbackstate.setName(rs.getString(FeedBackState.NAME_COLUMN));
        return feedbackstate;
    }

    private List<FeedBackState> getList(String sqlQuery) {
        List<FeedBackState> list = new ArrayList<FeedBackState>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(creater(rs));
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
        return list;
    }

    @Override
    public List<FeedBackState> findAll() {
        return getList(SQL_FIND_ALL_STATES);
    }

}
