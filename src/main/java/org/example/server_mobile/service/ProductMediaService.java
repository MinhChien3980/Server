package org.example.server_mobile.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.server_mobile.mapper.ProductMediaMapper;
import org.example.server_mobile.repository.ProductMediaRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductMediaService {
    ProductMediaMapper productMediaMapper;
    ProductMediaRepo productMediaRepo;

    public void deleteProductMedia(Long id) {
        productMediaRepo.deleteById(id);
    }

    public void deleteProductMediaByProductId(Long id) {
        productMediaRepo.deleteByProductId(id);
    }

}
