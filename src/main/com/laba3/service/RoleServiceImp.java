package com.laba3.service;

import com.laba3.dao.RoleDao;
import com.laba3.dao.RoleDaoImp;
import com.laba3.pojo.Role;

import java.util.Collection;

/**
 * Created by set on 23.04.17.
 */
public class RoleServiceImp implements RoleService {


    private static RoleDao roleDao = new RoleDaoImp();

    public Collection<Role> getAllRole() throws ClassNotFoundException {
        return roleDao.getAll();
    }

}
