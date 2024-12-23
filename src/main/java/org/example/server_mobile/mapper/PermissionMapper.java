package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.PermissionRequest;
import org.example.server_mobile.dto.response.PermissionResponse;
import org.example.server_mobile.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

}
