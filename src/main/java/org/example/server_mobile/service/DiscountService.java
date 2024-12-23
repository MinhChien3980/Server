package org.example.server_mobile.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.server_mobile.dto.request.DiscountRequest;
import org.example.server_mobile.dto.response.DiscountResponse;
import org.example.server_mobile.entity.Discount;
import org.example.server_mobile.mapper.DiscountMapper;
import org.example.server_mobile.repository.DiscountRepo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountService {
    DiscountRepo discountRepo;
    DiscountMapper discountMapper;

    public DiscountResponse createDiscount(DiscountRequest request) {
        Discount discount = discountMapper.toDiscount(request);
        discount = discountRepo.save(discount);
        return discountMapper.toDiscountResponse(discount);
    }

    public DiscountResponse getDiscountById(Long id) {
        Discount discount = discountRepo.findById(id).orElseThrow();
        return discountMapper.toDiscountResponse(discount);
    }
    public void deleteDiscount(Long id) {
        discountRepo.deleteById(id);
    }
    public DiscountResponse updateDiscount(Long id, DiscountRequest request) {
        Discount discount = discountRepo.findById(id).orElseThrow();
        discount = discountMapper.toDiscount(request);
        discount = discountRepo.save(discount);
        return discountMapper.toDiscountResponse(discount);
    }
}
