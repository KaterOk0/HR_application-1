package com.mycom.entity;

public class CandidateCompetence {
    public static final String TABLE_NAME = "candidatecompetence";
    public static final String SKILL_NAME = "skill";
    public static final String ID_CANDIDATE = "idCandidate";

    private String name;
    private long idCandidate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(long idCandidate) {
        this.idCandidate = idCandidate;
    }

}
