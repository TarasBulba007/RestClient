package com.tarasPlus.restClient.restService;

import com.tarasPlus.restClient.model.Role;

import java.util.List;

public interface RoleService  {

    List<Role> findAll();

    Role findById(Long id);
}
