package org.example.server_mobile.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.entity.AgeGroup;
import org.example.server_mobile.entity.Gender;
import org.example.server_mobile.entity.ProductMedia;
import org.example.server_mobile.entity.Size;
import org.example.server_mobile.entity.enums.AgeGroupCategory;

import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProductRequest {
    String name;
    String description;
    Double price;
    String status;
    Double stock;
    Size size;
    Gender gender;
    AgeGroup ageGroup;
    List<ProductMedia> productMedia;
}
