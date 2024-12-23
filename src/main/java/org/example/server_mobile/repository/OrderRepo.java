package org.example.server_mobile.repository;

import java.util.List;

import org.example.server_mobile.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
