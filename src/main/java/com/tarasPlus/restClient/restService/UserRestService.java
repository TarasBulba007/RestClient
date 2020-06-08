package com.tarasPlus.restClient.restService;

import com.tarasPlus.restClient.model.Role;
import com.tarasPlus.restClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class UserRestService implements UserService, RoleService {

    private RestTemplate restTemplate;
    private Role adminRole;
    private Role userRole;

    @Value("${central.server.url}")
    private String CENTRAL_SERVER_URL;

    @Autowired
    public UserRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addUser(User user) {
        restTemplate.postForEntity(CENTRAL_SERVER_URL + "/api/users", user, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        User[] users = restTemplate.getForObject(CENTRAL_SERVER_URL+"/api/users", User[].class);
        return Arrays.asList(users);
    }

    @Override
    public User getUserById(Long id) {
        User user = restTemplate.getForObject(CENTRAL_SERVER_URL + "/api/users/" + id, User.class, id);
        return user;
    }

    @Override
    public boolean updateUser(User user, Set<Role> roles) {
        if (notNullDataUser(user)){
        user.setRoles(roles);
        restTemplate.put(CENTRAL_SERVER_URL + "/api/users", user);
        return true;
        }
        return false;
    }

    public void deleteUser(Long id) {
        restTemplate.delete(CENTRAL_SERVER_URL + "/api/users/" + id, id);
    }

//    @Override
//    public boolean addUserAdmin(User user, String access) {
//        if (notNullDataUser(user) && !isExistLogin(user.getUsername())) {
//            initRole(user, access);
//            restTemplate.postForEntity(CENTRAL_SERVER_URL + "/api/users", user, User.class);
//        }
//        return false;
//    }

    public List<Role> findAll() {
        ResponseEntity<List> roles = restTemplate.getForEntity(CENTRAL_SERVER_URL + "/api/users/roles", List.class);
        return roles.getBody();
    }

    @Override
    public Role findById(Long id) {
        Role role = restTemplate.getForObject(CENTRAL_SERVER_URL+"/api/users/roles/" + id, Role.class, id);
        return role;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = restTemplate.getForObject(CENTRAL_SERVER_URL + "/api/users/name/" + username, User.class);
        if (user == null){
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        return user;
    }

    @Override
    public boolean notNullDataUser(User user) {
        return user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty() &&
                user.getEmail() != null && !user.getEmail().isEmpty();
    }

    @Override
    public boolean isExistLogin(String login) {
        return (restTemplate.getForObject(CENTRAL_SERVER_URL + "/api/users/name/" + login, User.class) != null);
    }

    private void initRole(User user, String access) {
        if (access.contains("ADMIN")) {
            addAdminRole(user);
        } else {
            addUserRole(user);
        }
    }

    private void addAdminRole(User user) {
        if (adminRole == null) {
            adminRole = findById(1L);
        }
        user.getRoles().add(adminRole);
    }

    private void addUserRole(User user) {
        if (userRole == null) {
            userRole = findById(2L);
        }
        user.getRoles().add(userRole);
    }
}
