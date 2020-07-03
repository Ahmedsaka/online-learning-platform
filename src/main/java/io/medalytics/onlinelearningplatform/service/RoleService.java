package io.medalytics.onlinelearningplatform.service;


import io.medalytics.onlinelearningplatform.dao.RoleDao;
import io.medalytics.onlinelearningplatform.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

}
