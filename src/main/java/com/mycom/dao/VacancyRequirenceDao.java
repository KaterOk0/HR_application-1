package com.mycom.dao;

import com.mycom.entity.VacancyRequirence;

import java.util.List;

public interface VacancyRequirenceDao {
    String SQL_FIND_BY_ID = "select " + VacancyRequirence.SKILL_COLUMN +
            " from " + VacancyRequirence.TABLE_NAME + " where "
            + " " + VacancyRequirence.ID_VACANCY_COLUMN + "=?";
    String SQL_INSERT_REQUIRENCE = SqlDao.SQL_INSERT + VacancyRequirence.TABLE_NAME +
            "(" + VacancyRequirence.ID_VACANCY_COLUMN + ""
            + "," + VacancyRequirence.SKILL_COLUMN + ") values(?,?)";
    String SQL_DELETE_REQUIRENCE = SqlDao.SQL_DELETE + VacancyRequirence.TABLE_NAME +
            " where " + VacancyRequirence.ID_VACANCY_COLUMN + "=?";

    List<String> findById(long id);

    void insert(VacancyRequirence vacancyrequirence);

    void delete(long vacancyId);
}
