package org.example.server_mobile.repository;

import org.example.server_mobile.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepo extends JpaRepository<Gender, Long> {
}
