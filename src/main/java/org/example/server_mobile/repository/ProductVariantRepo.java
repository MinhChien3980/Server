package org.example.server_mobile.repository;

import org.example.server_mobile.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> {
}
