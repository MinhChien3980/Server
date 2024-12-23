package org.example.server_mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.entity.Role;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
public class UserResponse {
    String id;
    String fullName;
    String email;
    Set<Role> role;
    LocalDate dateOfBirth;
    String status;
    String phoneNumber;
    Date created_at;
    Date updated_at;
}
