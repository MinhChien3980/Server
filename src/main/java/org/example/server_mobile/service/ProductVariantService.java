package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.dto.request.ProductVariantRequest;
import org.example.server_mobile.dto.response.ProductResponse;
import org.example.server_mobile.dto.response.ProductVariantResponse;
import org.example.server_mobile.entity.ProductVariant;
import org.example.server_mobile.mapper.ProductVariantMapper;
import org.example.server_mobile.repository.ProductVariantRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariantService {
    ProductVariantMapper productVariantMapper;
    ProductVariantRepo productVariantRepo;

    public ProductVariantResponse createProductVariant(ProductVariantRequest request) {
        ProductVariant productVariant = productVariantMapper.toProductVariant(request);
        productVariant = productVariantRepo.save(productVariant);
        return productVariantMapper.toProductVariantResponse(productVariant);
    }

//    public ProductVariantResponse updateProductVariant(Long id, ProductVariantRequest request) {
//        ProductVariant productVariant = productVariantRepo.findById(String.valueOf(id)).orElseThrow();
//        productVariantMapper.updateProductVariant(request, productVariant);
//        productVariant = productVariantRepo.save(productVariant);
//        return productVariantMapper.toProductVariantResponse(productVariant);
//    }

    public void deleteProductVariant(Long id) {
        productVariantRepo.deleteById(id);
    }

}
