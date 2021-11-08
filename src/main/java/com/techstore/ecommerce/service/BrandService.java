package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import com.techstore.ecommerce.object.mapper.BrandMapper;
import com.techstore.ecommerce.repository.jpa.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepo;
  private final BrandMapper brandMapper;

  public List<Brand> findAllBrands(){
    return brandRepo.findAll();
  }

  public Brand findBrandById(long id) {
    return brandRepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Not found any brand with id " + id));
  }

  public Brand createBrand(BrandRequest request) {
    Brand brand = brandMapper.createEntityFromRequest(request);
    existingBrand(brand);
    return brandRepo.save(brand);
  }

  public Brand updateBrand(long id, BrandRequest request) {
    Brand brand = findBrandById(id);
    brandMapper.update(brand, request);
    //TODO: kiểm tra tên giống như create
    return brandRepo.save(brand);
  }

  public void deleteBrand(long id) {
    Brand brand = findBrandById(id);
    brandRepo.delete(brand);
  }

  public void existingBrand(Brand brand){
    boolean existing = brandRepo.existsByName(brand.getName());
    if(existing){
      throw new EntityExistsException("Brand name "+brand.getName()+" already exists");
    }
  }
}
