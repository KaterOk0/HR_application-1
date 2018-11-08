package com.mycom.dao;

import com.mycom.entity.Vacancy;

import java.util.List;

public interface VacancyDao {
    String SQL_FIND_ALL_VACANCY = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME;
    String SQL_SORT_FOR_SALARY_TO = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME +
            " order by " + Vacancy.SALARY_TO_COLUMN;
    String SQL_SORT_FOR_SALARY_FROM = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME +
            " order by " + Vacancy.SALARY_FROM_COLUMN;
    String SQL_SORT_FOR_EXPERIENCE = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME +
            " order by " + Vacancy.EXPERIENCE_YEAR_REQUIRE_COLUMN;
    String SQL_FIND_FOR_CREATE = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME +
            " where " + Vacancy.ID_DEVELOPER + "=?";
    String SQL_FIND_BY_ID = SqlDao.SQL_FIND_ALL + Vacancy.TABLE_NAME +
            " where " + Vacancy.ID_COLUMN + "=?";
    String SQL_INSERT_VACANCY = SqlDao.SQL_INSERT + Vacancy.TABLE_NAME +
            "(" + Vacancy.POSITION_COLUMN + ", " + Vacancy.SALARY_TO_COLUMN + ""
            + ", " + Vacancy.SALARY_FROM_COLUMN + "," + Vacancy.EXPERIENCE_YEAR_REQUIRE_COLUMN +
            "," + Vacancy.ID_DEVELOPER + ")" + " values(?,?,?,?,?)";
    String SQL_UPDATE_VACANCY = SqlDao.SQL_UPDATE + Vacancy.TABLE_NAME +
            " set " + Vacancy.POSITION_COLUMN + "=?, " + Vacancy.SALARY_TO_COLUMN + "=?, "
            + "" + Vacancy.SALARY_FROM_COLUMN + "=?, " + Vacancy.EXPERIENCE_YEAR_REQUIRE_COLUMN +
            "=?, " + Vacancy.ID_DEVELOPER + "=? " + "where " + Vacancy.ID_COLUMN + "=?";
    String SQL_DELETE_VACANCY = SqlDao.SQL_DELETE + Vacancy.TABLE_NAME +
            " where " + Vacancy.ID_COLUMN + "=?";

    List<Vacancy> findAll();

    List<Vacancy> sortForSalaryTo();

    List<Vacancy> sortForSalaryFrom();

    List<Vacancy> sortForExperience();

    List<Vacancy> findByCreate(long idDeveloper);

    Vacancy findById(long id);

    void insert(Vacancy vacancy);

    void update(Vacancy vacancy);

    void delete(long vacancyId);

}
