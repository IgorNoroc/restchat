package com.igornoroc.restchat.service.impl;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PersonServiceImpl service;

    public UserDetailsServiceImpl(PersonServiceImpl service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = service.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("user %s not found!", username));
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapCollectionToGrandAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapCollectionToGrandAuthorities(Collection<Role> roles) {
        return roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getRole())).
                collect(Collectors.toList());
    }
}
