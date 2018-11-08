package com.mycom.dao;

import com.mycom.entity.CandidateState;

import java.util.List;

public interface CandidateStateDao {

    String SQL_FIND_ALL_STATES = SqlDao.SQL_FIND_ALL + CandidateState.TABLE_NAME;

    List<CandidateState> findAll();
}
