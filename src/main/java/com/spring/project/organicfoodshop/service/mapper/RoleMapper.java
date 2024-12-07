package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.request.management.role.CreateRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRole(CreateRoleRequest createRoleRequest);
}
