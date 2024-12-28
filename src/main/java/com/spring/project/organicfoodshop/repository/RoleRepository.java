package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, BaseRepository<Role, Long> {
    Role findByName(RoleEnum name);
}
