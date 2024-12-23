package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.mapper.AgeGroupMapper;
import org.example.server_mobile.repository.AgeGroupRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AgeService {
    AgeGroupMapper ageGroupMapper;
    AgeGroupRepo ageGroupRepo;

    public List<AgeGroup> allAgeGroup() {
        return ageGroupRepo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AgeGroup createAgeGroup(AgeGroup ageGroup) {
        return ageGroupRepo.save(ageGroup);
    }
}
