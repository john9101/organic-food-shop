package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByIdOrThrow(Long id) {
        return userRepository.findByIdOrThrow(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public Set<User> getAllUsersById(Set<Long> ids) {
        return Set.copyOf(userRepository.findAllById(ids));
    }

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }
    
    public Set<User> handleSaveUsers(Set<User> users) {
        return Set.copyOf(userRepository.saveAll(users));
    }

    public Optional<User> handleActivateUser(String activateKey) {
        return userRepository
                .findOneByActivationKey(activateKey)
                .map(user -> {
                    user.setActivated(true);
                    user.setActivationKey(null);
                    return user;
                });
    }
}
