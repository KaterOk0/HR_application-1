package com.mycom.dao;

import com.mycom.entity.Skill;

import java.util.List;

public interface SkillDao {
    String SQL_FIND_ALL_SKILLS = SqlDao.SQL_FIND_ALL + Skill.TABLE_NAME;
    String SQL_SORT_SKILL = SqlDao.SQL_FIND_ALL + Skill.TABLE_NAME +
            " order by " + Skill.NAME_COLUMN;
    String SQL_INSERT_SKILL = SqlDao.SQL_INSERT + Skill.TABLE_NAME +
            "(" + Skill.NAME_COLUMN + ") values(?)";
    String SQL_DELETE_SKILL = SqlDao.SQL_DELETE + Skill.TABLE_NAME +
            " where " + Skill.NAME_COLUMN + "=?";

    List<Skill> findAll();

    List<Skill> sortSkill();

    void insert(Skill skill);

    void delete(String name);
}
