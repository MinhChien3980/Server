package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.ProductRequest;
import org.example.server_mobile.dto.response.ProductResponse;
import org.example.server_mobile.entity.Product;
import org.example.server_mobile.util.StatusUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = StatusUtils.class)
public interface ProductMapper {
    @Mapping(target = "status", expression = "java(StatusUtils.statusToByte(request.getStatus()))")
    Product toProduct(ProductRequest request);

    ProductResponse toProductResponse(Product product);
}
