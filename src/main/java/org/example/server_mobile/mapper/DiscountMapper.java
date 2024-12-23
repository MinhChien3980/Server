package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.DiscountRequest;
import org.example.server_mobile.dto.response.DiscountResponse;
import org.example.server_mobile.entity.Discount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DiscountMapper {
    Discount toDiscount(DiscountRequest request);

    DiscountResponse toDiscountResponse(Discount discount);
}
