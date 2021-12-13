package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Role;

public interface RoleService {

    Role save(Role role);

    Role findById(Long id);

    void delete(Long id);

    void deleteAll();
}
