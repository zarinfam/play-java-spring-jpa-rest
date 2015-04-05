package services;

import models.Role;
import models.User;

import java.util.List;

/**
 * Created by mohsen on 4/5/15.
 */
public interface UserService {
    void addUser(User user);

    void addRole(Role role);

    void setRoleForUser(long userId, long roleId);

    void updateUserRoles(long userId, List<Long> roleIds);
}
