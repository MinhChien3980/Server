package org.example.server_mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.example.server_mobile.entity.CartItem;
import org.example.server_mobile.entity.CartPrice;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CartResponse {
    Long id;
    List<CartItemResponse> cartItems;
    Address address;
    CartPrice cartPrice;
    String errorMessage;
}
