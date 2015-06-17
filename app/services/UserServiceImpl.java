package services;

import daos.GeneralDao;
import models.Role;
import models.RoleOfUser;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsen on 4/5/15.
 */
@Named
@Transactional
public class UserServiceImpl implements UserService {
    @Inject
    GeneralDao generalDao;


    @Override
    public void addUser(User user) {
        generalDao.persist(user);
    }

    @Override
    public void addRole(Role role) {
        generalDao.persist(role);
    }

    @Override
    public void setRoleForUser(long userId, long roleId) {

        RoleOfUser roleOfUser = new RoleOfUser();
        roleOfUser.setRole(new Role(roleId));
        roleOfUser.setUser(new User(userId));

        generalDao.persist(roleOfUser);
    }

    @Override
    public void updateUserRoles(long userId, List<Long> roleIds) {
        roleIds.stream().forEach(roleId -> setRoleForUser(userId, roleId));
    }
}
