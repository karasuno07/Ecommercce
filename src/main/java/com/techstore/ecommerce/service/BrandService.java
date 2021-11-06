package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.entity.Brand;
import com.techstore.ecommerce.repository.jpa.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public Brand findBrandById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any brand with id " + id));
    }
}
