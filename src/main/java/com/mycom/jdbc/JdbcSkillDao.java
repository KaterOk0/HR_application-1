package com.mycom.jdbc;

import com.mycom.dao.SkillDao;
import com.mycom.entity.Skill;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("SkillDao")
public class JdbcSkillDao implements SkillDao {

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Skill creator(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setName(rs.getString(Skill.NAME_COLUMN));
        return skill;
    }

    private List<Skill> getList(String sqlQuery) {
        List<Skill> list = new ArrayList<Skill>();
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
    public List<Skill> findAll() {
        return getList(SQL_FIND_ALL_SKILLS);
    }

    @Override
    public List<Skill> sortSkill() {
        return getList(SQL_SORT_SKILL);
    }

    @Override
    public void insert(Skill skill) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SKILL);
            statement.setString(1, skill.getName());
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
    public void delete(String name) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SKILL);
            statement.setString(1, name);
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

}
