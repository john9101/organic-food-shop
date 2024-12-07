package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Permission;
import com.spring.project.organicfoodshop.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Set<Permission> getAllPermissionsById(Set<Long> ids) {
        return Set.copyOf(permissionRepository.findAllById(ids));
    }
}
