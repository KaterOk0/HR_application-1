package com.mycom.entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Vacancy {
    public static final String TABLE_NAME = "vacancy";
    public static final String ID_COLUMN = "id";
    public static final String SALARY_TO_COLUMN = "salaryTo";
    public static final String SALARY_FROM_COLUMN = "salaryFrom";
    public static final String EXPERIENCE_YEAR_REQUIRE_COLUMN = "experienceYearRequire";
    public static final String POSITION_COLUMN = "position";
    public static final String ID_DEVELOPER = "idDeveloper";

    private Long id;
    @DecimalMax("10000.0")
    @DecimalMin("200")
    private double salaryTo;
    @DecimalMax("10000.0")
    @DecimalMin("200")
    private double salaryFrom;
    @DecimalMax("30.0")
    @DecimalMin("0.0")
    private double experienceYearRequire;
    @Size(min = 5)
    private String position;
    private long idDeveloper;
    @NotNull
    private List<String> skills;
    private String developerName;


    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public long getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(long idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(double salaryTo) {
        this.salaryTo = salaryTo;
    }

    public double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public double getExperienceYearRequire() {
        return experienceYearRequire;
    }

    public void setExperienceYearRequire(double experienceYearRequire) {
        this.experienceYearRequire = experienceYearRequire;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
