package org.example.server_mobile.repository;

import org.example.server_mobile.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

    List<Role> findAllByNameIn(Set<String> roleNames);

}
