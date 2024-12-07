package com.spring.project.organicfoodshop.config;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
//import com.spring.project.organicfoodshop.domain.response.auth.InitializedPrincipalResponse;
import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.FormatErrorContentUtil;
import com.spring.project.organicfoodshop.util.constant.TargetSubjectEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
//    private LoggedInResponse.InitializedPrincipal initializedPrincipal;

    @Override
    public UserDetails loadUserByUsername(String email) throws AuthenticationException {
        User user = userService.getUserByEmail(email, true);

//        Set<Role> roles = user.getRoles();
//        Set<GrantedAuthority> grantedAuthorities = roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toSet());
//        grantedAuthorities.addAll(roles.stream()
//                        .flatMap(role -> role.getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getName())))
//                        .collect(Collectors.toSet()));

//        setInitializedPrincipal(UserMapper.INSTANCE.toInitializedPrincipal(user));
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
//                grantedAuthorities
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPermissions().forEach(permission ->
                    authorities.add(new SimpleGrantedAuthority(permission.getName().concat("_").concat(permission.getModule()))));
        });
        return authorities;
    }

}
