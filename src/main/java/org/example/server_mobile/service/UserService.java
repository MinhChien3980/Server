package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.constant.PredefinedRole;
import org.example.server_mobile.dto.request.UserCreationRequest;
import org.example.server_mobile.dto.response.UserResponse;
import org.example.server_mobile.entity.Role;
import org.example.server_mobile.entity.User;
import org.example.server_mobile.exception.AppException;
import org.example.server_mobile.exception.ErrorCode;
import org.example.server_mobile.mapper.UserMapper;
import org.example.server_mobile.repository.RoleRepo;
import org.example.server_mobile.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepo userRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepo roleRepo;

    public UserResponse createUser(UserCreationRequest userRequest) {

        if (userRepo.existsByEmail(userRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);

        User newUser = userMapper.toUser(userRequest);

        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setStatus((byte) ("ACTIVE".equals(userRequest.getStatus()) ? 1 : 0));
        HashSet<Role> roles = new HashSet<>();
        roleRepo.findById(Long.valueOf(PredefinedRole.USER_ROLE)).ifPresent(roles :: add);
        newUser.setRole(roles);
        newUser.setFullName(userRequest.getFullName());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        try {
            newUser = userRepo.save(newUser);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.toUserResponse(userRepo.save(newUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User entity: " + user);

        return userMapper.toUserResponse(user);
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse updateUser(Long id, UserCreationRequest user) {
        log.info("Updating user with id: {}", id);
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("Existing user: {}", existingUser.toString());
        userMapper.updateUser(user, existingUser);
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Updated user: {}", existingUser.getPassword());

        var roleNames = user.getRole().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        log.info("roleNames: {}", roleNames);
        var roles = roleRepo.findAllByNameIn((Set<String>) roleNames);
        log.info("roles: {}", roles);
        existingUser.setRole(new HashSet<>(roles));
        log.info("Updated user: {}", existingUser.getRole());
        return userMapper.toUserResponse(userRepo.save(existingUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String authented = context.getAuthentication().getName();

        User byUserName = userRepo.findByEmail(authented)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(byUserName);
    }
}
