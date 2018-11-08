package com.mycom.dao;

import com.mycom.entity.Interview;

import java.util.List;

public interface InterviewDao {
    String SQL_FIND_ALL_INTERVIEW = SqlDao.SQL_FIND_ALL + Interview.TABLE_NAME;
    String SQL_SORT_BY_DATE_FACT = SqlDao.SQL_FIND_ALL + Interview.TABLE_NAME +
            " order by " + Interview.FACT_DATE_COLUMN;
    String SQL_SORT_BY_DATE_PLAN = SqlDao.SQL_FIND_ALL + Interview.TABLE_NAME +
            " order by " + Interview.PLAN_DATE_COLUMN;
    String SQL_FIND_BY_CANDIDATE = SqlDao.SQL_FIND_ALL + Interview.TABLE_NAME +
            " where " + Interview.ID_CANDIDATE_COLUMN + "=?";
    String SQL_FIND_BY_ID = SqlDao.SQL_FIND_ALL + Interview.TABLE_NAME +
            " where " + Interview.ID_COLUMN + "=?";
    String SQL_INSERT_INTERVIEW = SqlDao.SQL_INSERT + Interview.TABLE_NAME +
            "(" + Interview.PLAN_DATE_COLUMN + "," + Interview.FACT_DATE_COLUMN + ","
            + "" + Interview.ID_CANDIDATE_COLUMN + "," + Interview.ID_VACANCY_COLUMN
            + "," + Interview.NAME_COLUMN + ") values(?,?,?,?,?)";
    String SQL_UPDATE_INTERVIEW = SqlDao.SQL_UPDATE + Interview.TABLE_NAME + " set " +
            Interview.PLAN_DATE_COLUMN + "=?, " + Interview.FACT_DATE_COLUMN + "=?,"
            + " " + Interview.ID_CANDIDATE_COLUMN + "=?, " +
            Interview.ID_VACANCY_COLUMN + "=?, " +
            Interview.NAME_COLUMN + "=? where " + Interview.ID_COLUMN + "=?";
    String SQL_DELETE_INTERVIEW = SqlDao.SQL_DELETE + Interview.TABLE_NAME +
            " where " + Interview.ID_COLUMN + "=?";

    List<Interview> findAll();

    List<Interview> sortByDateFact();

    List<Interview> sortByDatePlan();

    List<Interview> findByCandidate(long candidateId);

    Interview findById(long id);

    void insert(Interview interview);

    void update(Interview interview);

    void delete(long interviewId);

}
