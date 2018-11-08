package com.mycom.entity;

import javax.validation.constraints.Size;

public class Skill {
    public static final String TABLE_NAME = "skill";
    public static final String NAME_COLUMN = "name";
    @Size(min = 1)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
