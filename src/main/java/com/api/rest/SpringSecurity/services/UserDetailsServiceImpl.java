package com.api.rest.SpringSecurity.services;

import com.api.rest.SpringSecurity.entities.User;
import com.api.rest.SpringSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User '%s' not found.", username)
                ));

        List<SimpleGrantedAuthority> authorityList = user
                .getRoles()
                .stream()
                .flatMap((role) -> {
                    String roleName = String.format("ROLE_%s", role.getName().name());
                    return Stream.concat(
                            Stream.of(new SimpleGrantedAuthority(roleName)),
                            role
                            .getPermissions()
                            .stream()
                            .map((permission) -> new SimpleGrantedAuthority(permission.getName()))
                    );
                })
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isAccountNoLocked(),
                user.isCredentialNoExpired(),
                authorityList
        );
    }

}
