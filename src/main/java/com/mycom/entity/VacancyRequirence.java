package com.mycom.entity;

public class VacancyRequirence {
    public static final String TABLE_NAME = "vacancyrequirement";
    public static final String SKILL_COLUMN = "skill";
    public static final String ID_VACANCY_COLUMN = "idVacancy";

    private String skill;
    private long idVacancy;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public long getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(long idVacancy) {
        this.idVacancy = idVacancy;
    }


}
