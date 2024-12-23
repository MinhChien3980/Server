package org.example.server_mobile.repository;

import org.example.server_mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
