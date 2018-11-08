package com.mycom.entity;

import javax.validation.constraints.Size;

public class FeedBack {
    public static final String TABLE_NAME = "interviewfeedback";
    public static final String REASON_COLUMN = "reason";
    public static final String FEEDBACK_STATE_COLUMN = "feedbackState";
    public static final String ID_INTERVIEWER_COLUMN = "idInterviewer";
    public static final String ID_INTERVIEW_COLUMN = "idInterview";
    public static final String ID_COLUMN = "id";

    private Long id;
    @Size(min = 1)
    private String reason;
    private String feedbackState;
    private long idInterviewer;
    private long idInterview;
    private String interviewName;
    private String interviewerName;

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(String feedbackState) {
        this.feedbackState = feedbackState;
    }

    public long getIdInterviewer() {
        return idInterviewer;
    }

    public void setIdInterviewer(long idInterviewer) {
        this.idInterviewer = idInterviewer;
    }

    public long getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(long idInterview) {
        this.idInterview = idInterview;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
