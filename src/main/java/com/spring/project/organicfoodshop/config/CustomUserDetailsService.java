package com.spring.project.organicfoodshop.config;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws AuthenticationException {
        Optional<User> optionalUser = userService.getUserByEmail(usernameOrEmail)
                .or(() -> userService.getUserByUsername(usernameOrEmail));
        User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User with " + usernameOrEmail + " is not found in system"));

        Set<Role> roles = user.getRoles();
        Set<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet());
        grantedAuthorities
                .addAll(roles.stream()
                        .flatMap(role -> role.getPermissions().stream()
                                .map(permission -> new SimpleGrantedAuthority(permission.getName()))).collect(Collectors.toSet()));

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                grantedAuthorities
        );
    }
}
