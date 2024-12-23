package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.RoleRequest;
import org.example.server_mobile.dto.response.RoleResponse;
import org.example.server_mobile.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
