package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.util.constant.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, BaseRepository<Role, Long> {
    Role findByName(RoleTypeEnum name);
}
