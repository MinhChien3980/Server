package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.UserCreationRequest;
import org.example.server_mobile.dto.response.UserResponse;
import org.example.server_mobile.entity.User;
import org.example.server_mobile.util.StatusUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = StatusUtils.class)
public interface UserMapper {
    @Mapping(target = "status", expression = "java(StatusUtils.statusToByte(userRequest.getStatus()))")
    User toUser(UserCreationRequest userRequest);

    UserResponse toUserResponse(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", expression = "java(StatusUtils.statusToByte(userRequest.getStatus()))")
    void updateUser(UserCreationRequest userRequest, @MappingTarget User user);

//    default Byte statusToByte(String status) {
//        if ("ACTIVE".equalsIgnoreCase(status)) {
//            return 1;
//        } else if ("INACTIVE".equalsIgnoreCase(status)) {
//            return 0;
//        }
//        return 0;
//    }
}
