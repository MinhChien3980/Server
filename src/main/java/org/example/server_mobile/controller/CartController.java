package org.example.server_mobile.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.example.server_mobile.dto.request.AddToCardRequest;
import org.example.server_mobile.dto.request.AddressRequest;
import org.example.server_mobile.dto.request.ApiResponse;
import org.example.server_mobile.dto.request.DeleteCartItemRequest;
import org.example.server_mobile.dto.request.UpdateCartQuanlityRequest;
import org.example.server_mobile.dto.response.CartResponse;
import org.example.server_mobile.entity.Address;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.entity.Cart;
import org.example.server_mobile.entity.CartPrice;
import org.example.server_mobile.mapper.CartMapper;
import org.example.server_mobile.service.AgeService;
import org.example.server_mobile.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CartController {

    CartService cartService;

    @PostMapping("/create")
    ApiResponse<CartResponse> createCart(@RequestParam Long userId) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.createCart(userId))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<CartResponse> getCart(@RequestParam Long id) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.getCart(id))
                .build();
    }

    @PostMapping("/{id}/addToCart")
    public ApiResponse<String> addToCart(@RequestParam Long cartId, @RequestBody AddToCardRequest addToCardRequest) {
        cartService.addToCart(cartId, addToCardRequest.getProductId(),
                addToCardRequest.getQuantity());
        return ApiResponse.<String>builder().data("Product added to cart").code(200).build();
    }

    @PostMapping("/{id}/update")
    public ApiResponse<String> updateCart(@RequestParam Long cartId,
            @RequestBody UpdateCartQuanlityRequest addToCardRequest) {
        cartService.updateCart(cartId, addToCardRequest.getProductId(),
                addToCardRequest.getQuantity());
        return ApiResponse.<String>builder().data("Product updated in cart").code(200).build();
    }

    @PostMapping("/{id}/delete")
    public ApiResponse<String> deleteFromCart(@RequestParam Long cartId,
            @RequestBody DeleteCartItemRequest addToCardRequest) {
        cartService.removeFromCart(cartId, addToCardRequest.getProductId());
        return ApiResponse.<String>builder().data("Product deleted from cart").code(200).build();
    }

    @PostMapping("/{id}/setAddress")
    public ApiResponse<String> setAddress(@RequestParam Long cartId, @RequestParam AddressRequest address) {
        cartService.setAddress(address, cartId);
        return ApiResponse.<String>builder().data("Address set").code(200).build();
    }
}
