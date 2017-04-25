package main.com.laba3.service;

import main.com.laba3.dao.RoleDao;
import main.com.laba3.dao.RoleDaoImp;
import main.com.laba3.pojo.Role;

import java.util.Collection;
import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public class RoleServiceImp implements RoleService{


    private static RoleDao roleDao = new RoleDaoImp();

    public Collection<Role> getAllRole() throws ClassNotFoundException {
        return (List<Role>)roleDao.getAll();
    }

}
