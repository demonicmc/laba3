package main.com.laba3.pojo;

import java.util.logging.Logger;

/**
 * Created by set on 23.04.17.
 */
public class Role {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        if (this.id !=(((Role) obj).id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) getId();
        result = 32 * result;
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
