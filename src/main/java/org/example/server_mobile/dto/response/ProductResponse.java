package org.example.server_mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.entity.Discount;
import org.example.server_mobile.entity.Gender;
import org.example.server_mobile.entity.Size;

import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProductResponse {
    String id;
    String name;
    String description;
    Double price;
    String status;
    Double stock;
    Size size;
    Gender gender;
    AgeGroup ageGroup;
    List<ProductVariantResponse> productVariants;
    List<ProductMediaResponse> productMedia;
    Set<DiscountResponse> discount;
    String created_at;
    String updated_at;
}
