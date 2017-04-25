package main.com.laba3.pojo;

import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Created by set on 23.04.17.
 */
public class History {

    private long id;
    private long id_user;
    private User user;
    private Timestamp date;
    private String messget;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getMessget() {
        return messget;
    }

    public void setMessget(String messget) {
        this.messget = messget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;

        History history = (History) o;

        return id == history.id;
    }

    @Override
    public int hashCode() {
        int result = (int) getId();
        result = 32 * result;
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", user=" + user +
                ", date=" + date +
                ", messget='" + messget + '\'' +
                '}';
    }
}
