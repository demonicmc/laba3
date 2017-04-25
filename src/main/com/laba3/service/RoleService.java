package main.com.laba3.service;

import main.com.laba3.pojo.Role;

import java.util.Collection;

/**
 * Created by set on 23.04.17.
 */
public interface RoleService {

    public Collection<Role> getAllRole() throws ClassNotFoundException;

}
