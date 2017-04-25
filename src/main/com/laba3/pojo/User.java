package main.com.laba3.pojo;


import java.util.logging.Logger;

/**
 * Created by set on 23.04.17.
 */
public class User {

    private long id;
    private String loggin;
    private String password;
    private String mail;
    private long role_id;
    private Role role;


    public User() {
    }

    public User(long id, String loggin, String password, String mail, long role_id) {
        this.id = id;
        this.loggin = loggin;
        this.password = password;
        this.mail = mail;
        this.role_id = role_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        if (this.id !=(((User) obj).id)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loggin='" + loggin + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", role_id=" + role +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (int) getId();
        result = 32 * result;
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoggin() {
        return loggin;
    }

    public void setLoggin(String loggin) {
        this.loggin = loggin;
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
