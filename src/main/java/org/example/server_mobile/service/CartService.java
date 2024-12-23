package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.example.server_mobile.dto.request.AddressRequest;
import org.example.server_mobile.dto.response.CartResponse;
import org.example.server_mobile.entity.Address;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.entity.Cart;
import org.example.server_mobile.entity.CartItem;
import org.example.server_mobile.entity.User;
import org.example.server_mobile.exception.ApiException;
import org.example.server_mobile.exception.AppException;
import org.example.server_mobile.mapper.AddressMapper;
import org.example.server_mobile.mapper.AgeGroupMapper;
import org.example.server_mobile.mapper.CartItemMapper;
import org.example.server_mobile.mapper.CartMapper;
import org.example.server_mobile.repository.AgeGroupRepo;
import org.example.server_mobile.repository.CartRepo;
import org.example.server_mobile.repository.ProductRepo;
import org.example.server_mobile.repository.ProductVariantRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepo cartRepo;
    CartMapper cartMapper;
    CartItemMapper cartItemMapper;
    ProductVariantRepo productVariantRepo;
    AddressMapper addressMapper;

    public CartResponse getCart(Long id) {
        Cart cart = cartRepo.findById(id).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        var cartResponse = cartMapper.toCartResponse(cart);
        validation(cart, cartResponse);
        return cartResponse;
    }

    public CartResponse findByUserId(Long userId) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        var cartResponse = cartMapper.toCartResponse(cart);
        validation(cart, cartResponse);
        return cartResponse;
    }

    public CartResponse createCart(Long userId) {
        Cart cart = Cart.builder().user(User.builder().id(userId).build()).cartItems(new ArrayList<>())
                .build();
        cartRepo.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    private void validation(Cart cart, CartResponse cartResponse) {
        String cartErrorMessage = null;
        var cartItems = cart.getCartItems();

        // check quantity of product in cart
        {
            var carItemsResponse = cartItems.stream()
                    .map(cartItemMapper::toCartItemResponse)
                    .map(cartItemResponse -> {
                        String itemCartLineMessage;
                        var productVariant = cartItemResponse.getProductVariant();
                        var product = productVariant.getProduct();
                        var quantity = cartItemResponse.getQuantity();
                        var stock = productVariant.getStock();
                        if (stock < quantity) {
                            itemCartLineMessage = "Not enough stock for product " + product.getName();
                            cartResponse.setErrorMessage(itemCartLineMessage);
                        }

                        return cartItemResponse;

                    })
                    .collect(Collectors.toList());
            int errorCartItem = (int) carItemsResponse.stream()
                    .filter(cartItemResponse -> cartItemResponse.getErrorMessage() != null).count();
            if (errorCartItem > 0 && cartErrorMessage == null) {
                cartErrorMessage = "Some products are not available";
                cartResponse.setErrorMessage(cartErrorMessage);
            }
            cartResponse.setCartItems(carItemsResponse);
        }

    }

    public void addToCart(Long cartId, Long productVariantId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        var cartItems = cart.getCartItems();
        var productVariant = productVariantRepo.findById(productVariantId).orElseThrow(
                () -> new ApiException(404, "Product variant not found"));
        var cartItem = cartItems.stream()
                .filter(item -> item.getProductVariant().getId().equals(productVariantId))
                .findFirst()
                .orElse(null);
        if (productVariant.getStock() < quantity) {
            throw new ApiException(400, "Not enough stock for product");
        }
        if (cartItem == null) {
            cartItem = CartItem.builder()
                    .productVariant(productVariant)
                    .quantity(quantity)
                    .build();
            cartItems.add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cart.setCartItems(cartItems);
        cartRepo.save(cart);
    }

    public void updateCart(Long cartId, Long productVariantId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        var cartItems = cart.getCartItems();
        var cartItem = cartItems.stream()
                .filter(item -> item.getProductVariant().getId().equals(productVariantId))
                .findFirst()
                .orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
        }
        cart.setCartItems(cartItems);
        cartRepo.save(cart);
    }

    public void removeFromCart(Long cartId, Long productVariantId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        var cartItems = cart.getCartItems();
        var cartItem = cartItems.stream()
                .filter(item -> item.getProductVariant().getId().equals(productVariantId))
                .findFirst()
                .orElse(null);
        if (cartItem != null) {
            cartItems.remove(cartItem);
        }
        cart.setCartItems(cartItems);
        cartRepo.save(cart);
    }

    public void setAddress(AddressRequest addressRequest, Long cartId) {
        Address address = addressMapper.toAddress(addressRequest);
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        cart.setAddress(address);
        cartRepo.save(cart);
    }

    public void checkUserPermissionCart(Long userId, Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ApiException(404, "Cart not found"));
        if (!cart.getUser().getId().equals(userId)) {
            throw new ApiException(403, "Forbidden");
        }
    }
}
