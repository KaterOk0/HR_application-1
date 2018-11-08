package com.mycom.dao;

import com.mycom.entity.Candidate;

import java.util.List;

public interface CandidateDao {
    String SQL_FIND_ALL_CANDIDATES = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME;
    String SQL_SORT_SALARY = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " order by " + Candidate.SALARY_COLUMN;
    String SQL_FIND_BY_STATE = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " where " + Candidate.CANDIDATE_STATE_COLUMN + "=?";
    String SQL_SORT_NAME = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " order by " + Candidate.NAME_COLUMN;

    String SQL_SORT_SURNAME = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " order by " + Candidate.SURNAME_COLUMN;
    String SQL_SORT_BIRTHDAY = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " order by " + Candidate.BIRTHDAY_COLUMN;

    String SQL_FIND_BY_ID = SqlDao.SQL_FIND_ALL + Candidate.TABLE_NAME +
            " where " + Candidate.ID_COLUMN + "=?";
    String SQL_INSERT_CANDIDATE = SqlDao.SQL_INSERT + Candidate.TABLE_NAME +
            "(" + Candidate.NAME_COLUMN + "," + Candidate.SURNAME_COLUMN + ","
            + "" + Candidate.SALARY_COLUMN + "," + Candidate.BIRTHDAY_COLUMN +
            "," + Candidate.CANDIDATE_STATE_COLUMN + ") values(?,?,?,?,?)";
    String SQL_UPDATE_CANDIDATE = SqlDao.SQL_UPDATE + Candidate.TABLE_NAME +
            " set " + Candidate.NAME_COLUMN + "=?," + Candidate.SURNAME_COLUMN + "=?,"
            + "" + Candidate.SALARY_COLUMN + "=?," + Candidate.BIRTHDAY_COLUMN + "=?," +
            Candidate.CANDIDATE_STATE_COLUMN + "=?"
            + " where " + Candidate.ID_COLUMN + "=?";
    String SQL_DELETE_CANDIDATE = SqlDao.SQL_DELETE + Candidate.TABLE_NAME + " where " +
            Candidate.ID_COLUMN + "=?";

    List<Candidate> findAll();

    List<Candidate> sortSalaryCandidate();

    List<Candidate> findByState(String state);

    Candidate findById(long id);

    List<Candidate> sortNameCandidate();
    List<Candidate> sortSurNameCandidate();

    List<Candidate> sortBirthdayCandidate();

    void insert(Candidate candidate);

    void update(Candidate candidate);

    void delete(long candidateId);


}
