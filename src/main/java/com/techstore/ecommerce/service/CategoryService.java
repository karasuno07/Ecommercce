package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.repository.jpa.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category findCategoryById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any category with id " + id));
    }
}
