package com.mycom.dao;

import com.mycom.entity.CandidateCompetence;

import java.util.List;

public interface CandidateCompetenceDao {
    String SQL_FIND_SKILL = "select " + CandidateCompetence.SKILL_NAME +
            " from " + CandidateCompetence.TABLE_NAME +
            " where " + CandidateCompetence.ID_CANDIDATE + "=?";
    String SQL_INSERT_SKILL = SqlDao.SQL_INSERT + CandidateCompetence.TABLE_NAME +
            "(" + CandidateCompetence.ID_CANDIDATE + ","
            + CandidateCompetence.SKILL_NAME + ") values(?,?)";
    String SQL_DELETE_SKILL = SqlDao.SQL_DELETE + CandidateCompetence.TABLE_NAME +
            " where " + CandidateCompetence.ID_CANDIDATE + "=?";

    List<String> findSkill(long id);

    void insert(CandidateCompetence candidatecompetence);

    void delete(long id);

}
