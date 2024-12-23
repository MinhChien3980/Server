package org.example.server_mobile.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.dto.request.ApiResponse;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.service.AgeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/age")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AgeController {
    AgeService ageService;

    @PostMapping
    ApiResponse<AgeGroup> createAgeGroup(@RequestBody AgeGroup ageGroup) {
        return ApiResponse.<AgeGroup>builder()
                .data(ageService.createAgeGroup(ageGroup))
                .build();
    }

    @GetMapping
    ApiResponse<List<AgeGroup>> allAgeGroup() {
        return ApiResponse.<List<AgeGroup>>builder()
                .data(ageService.allAgeGroup())
                .build();
    }
}
