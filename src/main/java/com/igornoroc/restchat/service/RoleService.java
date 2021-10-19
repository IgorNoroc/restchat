package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Role;

public interface RoleService {

    Role save(Role role);

    Role findRoleById(long id);

    void delete(Role role);

    void deleteAll();
}
