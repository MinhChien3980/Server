package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.AgeGroupRequest;
import org.example.server_mobile.dto.response.AgeGroupResponse;
import org.example.server_mobile.entity.AgeGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgeGroupMapper {
    AgeGroup toAgeGroup(AgeGroupRequest ageGroup);

    AgeGroupResponse toAgeGroupResponse(AgeGroup ageGroup);
}
