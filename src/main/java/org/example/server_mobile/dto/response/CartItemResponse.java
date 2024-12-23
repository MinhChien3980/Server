package org.example.server_mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

import org.example.server_mobile.entity.Product;
import org.example.server_mobile.entity.ProductVariant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CartItemResponse {
    Long id;
    Product product;
    String productName;
    ProductVariant productVariant;
    Integer quantity;
    int grandTotal;
    int discount;
    int productPrice;
    String errorMessage;
}
