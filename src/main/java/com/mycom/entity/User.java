package com.mycom.entity;


import com.mycom.entity.validation.Email;
import com.mycom.entity.validation.Name;
import com.mycom.entity.validation.Password;

public class User {
    public static final String TABLE_NAME = "user";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String SURNAME_COLUMN = "surname";
    public static final String PASSWORD_COLUMN = "password";
    public static final String EMAIL_COLUMN = "email";
    public static final String ROLE_COLUMN = "role";

    private Long id;

    @Name
    private String name;
    @Name
    private String surname;

    @Email
    private String email;
    @Password
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
