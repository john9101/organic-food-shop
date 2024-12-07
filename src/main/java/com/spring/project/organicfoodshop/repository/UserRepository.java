package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, BaseRepository<User, Long>  {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    User findByActivationToken(String activationToken);
}
