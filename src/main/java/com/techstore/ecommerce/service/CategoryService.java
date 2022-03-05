package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.object.dto.mapper.CategoryMapper;
import com.techstore.ecommerce.object.model.Sorting;
import com.techstore.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }

    public Category findCategoryById(long id) {
        return categoryRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any category with id " + id));
    }

    public Category createCategory(CategoryRequest request) {
        Category category = categoryMapper.createEntityFromRequest(request);
        existingCategory(category);
        setCategoryById(category, request);
        System.out.println(category);
        return categoryRepo.save(category);
    }

    public Category updateCategory(long id, CategoryRequest request) {
        Category category = findCategoryById(id);
        categoryMapper.update(category, request);
        setCategoryById(category, request);
        return categoryRepo.save(category);
    }

    public void deleteCategory(long id) {
        Category category = findCategoryById(id);
        categoryRepo.delete(category);
    }

    protected void existingCategory(Category category){
        boolean existing = categoryRepo.existsByName(category.getName());
        if(existing){
            throw new EntityExistsException("Category name " + category.getName() + " already exists");
        }
    }

    private void setCategoryById(Category category, CategoryRequest request){
        if(request.getParentId() != null){
           Category categoryParent = categoryRepo.findById(request.getParentId()).orElseThrow(
                   () -> new ResourceNotFoundException("Not found any category parent with id " + request.getParentId())
           );

           category.setParent(categoryParent);
        }
    }

    private Sort getSort(Sorting sorting){
        return ObjectUtils.isEmpty(sorting)
                ? Sort.unsorted()
                : Sort.by(sorting.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC,
                sorting.getFieldName());
    }
}
