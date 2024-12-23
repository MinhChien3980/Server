package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.response.CartResponse;
import org.example.server_mobile.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    
    CartResponse toCartResponse(Cart cart);
}
