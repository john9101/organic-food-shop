package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByActivationToken(String activationToken);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    List<User> findAllByRolesContaining(Role role);
}

