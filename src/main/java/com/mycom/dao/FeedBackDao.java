package com.mycom.dao;

import com.mycom.entity.FeedBack;

import java.util.List;

public interface FeedBackDao {
    String SQL_FIND_ALL_FEEDBACK = SqlDao.SQL_FIND_ALL + FeedBack.TABLE_NAME;
    String SQL_FIND_BY_INTERVIEWER = SqlDao.SQL_FIND_ALL + FeedBack.TABLE_NAME +
            " where " + FeedBack.ID_INTERVIEWER_COLUMN + "=?";
    String SQL_FIND_BY_STATE = SqlDao.SQL_FIND_ALL + FeedBack.TABLE_NAME +
            " where " + FeedBack.FEEDBACK_STATE_COLUMN + "=?";
    String SQL_FIND_BY_ID = SqlDao.SQL_FIND_ALL + FeedBack.TABLE_NAME +
            " where " + FeedBack.ID_COLUMN + "=?";
    String SQL_INSERT_FEEDBACK = SqlDao.SQL_INSERT + FeedBack.TABLE_NAME +
            "(" + FeedBack.ID_INTERVIEW_COLUMN + "," + FeedBack.ID_INTERVIEWER_COLUMN +
            "," + FeedBack.FEEDBACK_STATE_COLUMN + "," +
            FeedBack.REASON_COLUMN + ") values(?,?,?,?)";
    String SQL_UPDATE_FEEDBACK = SqlDao.SQL_UPDATE + FeedBack.TABLE_NAME + " set " +
            FeedBack.ID_INTERVIEW_COLUMN + "=?," + FeedBack.ID_INTERVIEWER_COLUMN + ""
            + "=?," + FeedBack.FEEDBACK_STATE_COLUMN + "=?," +
            FeedBack.REASON_COLUMN + "=? where " + FeedBack.ID_COLUMN + "=?";
    String SQL_DELETE_FEEDBACK = SqlDao.SQL_DELETE + FeedBack.TABLE_NAME +
            " where " + FeedBack.ID_COLUMN + "=?";

    List<FeedBack> findAll();

    List<FeedBack> findByInterviewer(long interviewerId);

    List<FeedBack> findByState(String feedbackState);

    FeedBack findById(long id);

    void insert(FeedBack feedback);

    void update(FeedBack feedback);

    void delete(long feedbackId);
}

