package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.ProductVariantRequest;
import org.example.server_mobile.dto.response.ProductVariantResponse;
import org.example.server_mobile.entity.ProductVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toProductVariant(ProductVariantRequest request);

    ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

//    void updateProductVariant(ProductVariantRequest request, ProductVariant productVariant);
}
