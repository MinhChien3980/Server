package org.example.server_mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.entity.Gender;
import org.example.server_mobile.entity.OrderItem;
import org.example.server_mobile.entity.Size;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProductVariantResponse {
    String id;
    String name;
    String color;
    String sku;
    Double price;
    Double stock;
    String created_at;
    String updated_at;
}
