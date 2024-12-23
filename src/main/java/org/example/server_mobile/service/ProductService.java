package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.dto.request.ProductRequest;
import org.example.server_mobile.dto.response.ProductResponse;
import org.example.server_mobile.entity.Product;
import org.example.server_mobile.mapper.ProductMapper;
import org.example.server_mobile.repository.ProductRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepo productRepo;
    ProductMapper productMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        product.setStatus((byte) ("ACTIVE".equals(request.getStatus()) ? 1 : 0));
        return productMapper.toProductResponse(productRepo.save(product));
    }

    public List<Product> allProduct() {
        return productRepo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

}
