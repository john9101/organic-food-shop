package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.repository.RoleRepository;
import com.spring.project.organicfoodshop.repository.UserRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User getUserByIdOrThrow(Long id) {
        return userRepository.findByIdOrThrow(id);
    }

    public User getUserByEmail(String email, boolean isAcceptThrowAuthenticationException) {
        String exceptionMessage = FormatterUtil.formateExistExceptionMessage("email", email, ModuleEnum.ACCOUNT);
        return userRepository.findByEmail(email).orElseThrow(() ->
                isAcceptThrowAuthenticationException ?
                        new UsernameNotFoundException(exceptionMessage) :
                        new EntityNotFoundException(exceptionMessage)
        );
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.CUSTOMER)));
    }

    public Boolean ixExistsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean isExistsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Set<User> getAllUsersById(Set<Long> ids) {
        return Set.copyOf(userRepository.findAllById(ids));
    }

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllCustomers() {
        return userRepository.findAllByRolesContaining(roleRepository.findByName(RoleEnum.CUSTOMER));
    }

    public List<User> getAllEmployees() {
        return userRepository.findAllByRolesContaining(roleRepository.findByName(RoleEnum.EMPLOYEE));
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
        user.setIsActivated(true);
        user.setActivationToken(null);
        handleSaveUser(user);
    }
}
