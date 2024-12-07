package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> , BaseRepository<Permission, Long>{

}
