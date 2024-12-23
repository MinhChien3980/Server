package org.example.server_mobile.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.server_mobile.dto.request.ApiResponse;
import org.example.server_mobile.dto.request.PermissionRequest;
import org.example.server_mobile.dto.response.PermissionResponse;
import org.example.server_mobile.service.PermissonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissonService permissonService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permission) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissonService.create(permission))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissonService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<String> delete(@PathVariable Long permission) {
        permissonService.delete(permission);
        return ApiResponse.<String>builder()
                .data("Permission deleted")
                .build();
    }
}
