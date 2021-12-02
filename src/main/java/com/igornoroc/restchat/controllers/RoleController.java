package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Role;
import com.igornoroc.restchat.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public void addRole(@RequestBody Role role) {
        roleService.save(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteRole(@RequestBody Role role) {
        roleService.delete(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/All")
    public void deleteAllRoles() {
        roleService.deleteAll();
    }
}
