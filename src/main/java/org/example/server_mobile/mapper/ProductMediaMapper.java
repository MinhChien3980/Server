package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.ProductMediaRequest;
import org.example.server_mobile.dto.response.ProductMediaResponse;
import org.example.server_mobile.entity.ProductMedia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMediaMapper {
    ProductMedia toProductMedia(ProductMediaRequest request);

    ProductMediaResponse toProductMediaResponse(ProductMedia productMedia);
}
