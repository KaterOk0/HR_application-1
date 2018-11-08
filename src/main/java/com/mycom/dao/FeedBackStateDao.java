package com.mycom.dao;

import com.mycom.entity.FeedBackState;

import java.util.List;

public interface FeedBackStateDao {
    String SQL_FIND_ALL_STATES = SqlDao.SQL_FIND_ALL + FeedBackState.TABLE_NAME;

    List<FeedBackState> findAll();
}
