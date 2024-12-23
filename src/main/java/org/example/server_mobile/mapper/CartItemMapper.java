package org.example.server_mobile.mapper;
import org.example.server_mobile.dto.response.CartItemResponse;
import org.example.server_mobile.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse toCartItemResponse(CartItem cartItem);
}