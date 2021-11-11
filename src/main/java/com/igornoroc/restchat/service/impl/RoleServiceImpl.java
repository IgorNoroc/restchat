package com.igornoroc.restchat.service.impl;

import com.igornoroc.restchat.entities.Role;
import com.igornoroc.restchat.repositories.RoleRepo;
import com.igornoroc.restchat.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public Role findById(long id) {
        return roleRepo.findById(id).orElse(null);
    }

    public void delete(Role role) {
        roleRepo.delete(role);
    }

    public void deleteAll() {
        roleRepo.deleteAll();
    }
}
