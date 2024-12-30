package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.repository.RoleRepository;
import com.spring.project.organicfoodshop.util.constant.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role handleCreateRole(Role role) {
        return roleRepository.save(role);
    }

    public Set<Role> getAllRoles() {
        return Set.copyOf(roleRepository.findAll());
    }

    public Role getRoleByIdOrThrow(Long id) {
        return roleRepository.findByIdOrThrow(id);
    }

    public Set<Role> getAllRolesById(Set<Long> ids) {
        return Set.copyOf(roleRepository.findAllById(ids));
    }

    public Role getRoleByName(RoleEnum name) {
        return roleRepository.findByName(name);
    }
}
