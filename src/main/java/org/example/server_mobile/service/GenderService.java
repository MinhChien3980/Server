package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.mapper.GenderMapper;
import org.example.server_mobile.repository.GenderRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class GenderService {
    GenderRepo genderRepo;
    GenderMapper genderMapper;

}
