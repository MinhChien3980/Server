package org.example.server_mobile.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.server_mobile.dto.request.PermissionRequest;
import org.example.server_mobile.dto.response.PermissionResponse;
import org.example.server_mobile.entity.Permission;
import org.example.server_mobile.mapper.PermissionMapper;
import org.example.server_mobile.repository.PermissionRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissonService {
    PermissionRepo permissionRepo;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest resquest) {
        Permission permission = permissionMapper.toPermission(resquest);
        permission = permissionRepo.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepo.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(Long permission) {
        permissionRepo.deleteById(permission);
    }
}
