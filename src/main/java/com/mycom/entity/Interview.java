package com.mycom.entity;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Interview {
    public static final String TABLE_NAME = "interview";
    public static final String ID_COLUMN = "id";
    public static final String FACT_DATE_COLUMN = "factDate";
    public static final String PLAN_DATE_COLUMN = "planDate";
    public static final String ID_VACANCY_COLUMN = "idVacancy";
    public static final String ID_CANDIDATE_COLUMN = "idCandidate";
    public static final String NAME_COLUMN = "name";


    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime factDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime planDate;
    private long idVacancy;
    private long idCandidate;
    @Size(min = 5)
    private String name;
    private String candidateName;
    private String vacancyName;


    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(long idVacancy) {
        this.idVacancy = idVacancy;
    }

    public long getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(long idCandidate) {
        this.idCandidate = idCandidate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getFactDate() {
        return factDate;
    }

    public void setFactDate(DateTime factDate) {
        this.factDate = factDate;
    }

    public DateTime getPlanDate() {
        return planDate;
    }

    public void setPlanDate(DateTime planDate) {
        this.planDate = planDate;
    }


}
