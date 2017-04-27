package com.laba3.pojo;


/**
 * Created by set on 23.04.17.
 */
public class User {

    private long id;
    private String login;
    private String password;
    private String mail;
    private int role_id;
    private Role role;


    public User() {
    }

    public User(long id, String login, String password, String mail, int role_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }





    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loggin='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", role_id=" + role_id +
                '}';
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role_id) {
        this.role = role_id;
    }
}
