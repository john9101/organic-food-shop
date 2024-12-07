package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.repository.UserRepository;
import com.spring.project.organicfoodshop.util.FormatErrorContentUtil;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.constant.TargetSubjectEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByIdOrThrow(Long id) {
        return userRepository.findByIdOrThrow(id);
    }

    public User getUserByEmail(String email, boolean isAcceptThrowFailedAuthentication) {
        String notFoundExceptionMessage = FormatErrorContentUtil.decorateNotFoundEntityErrorContent("email", email, TargetSubjectEnum.ACCOUNT);
        return userRepository.findByEmail(email).orElseThrow(() ->
                isAcceptThrowFailedAuthentication ?
                        new UsernameNotFoundException(notFoundExceptionMessage) :
                        new EntityNotFoundException(notFoundExceptionMessage)
        );
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Set<User> getAllUsersById(Set<Long> ids) {
        return Set.copyOf(userRepository.findAllById(ids));
    }

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    public User handleAssignRoleToEmployee(User employee, String delegatePassword, Set<Role> roles){
        Optional<String> currentDelegateCredentialsOptional = SecurityUtil.getCurrentUserCredentials();
        String delegateCredentials = currentDelegateCredentialsOptional.orElse(null);
        if (passwordEncoder.matches(delegateCredentials, delegatePassword)) {
            employee.setRoles(roles);
        }
        return handleSaveUser(employee);
    }

    public void handleActivate(String activationToken) {
        User user = userRepository.findByActivationToken(activationToken);
        user.setActivated(true);
        user.setActivationToken(null);
        handleSaveUser(user);
    }
}
