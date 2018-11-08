package com.mycom.entity;

public class CandidateState {
    public static final String TABLE_NAME = "candidatestate";
    public static final String NAME_COLUMN = "name";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
