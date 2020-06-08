package com.tarasPlus.restClient.restService;

import com.tarasPlus.restClient.model.Role;
import com.tarasPlus.restClient.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    boolean updateUser (User user, Set<Role> roles);

    void addUser(User user);

    boolean addUserAdmin(User user, String access);

    List<Role> findAll();

    boolean notNullDataUser(User user);

    boolean isExistLogin(String login);
}
