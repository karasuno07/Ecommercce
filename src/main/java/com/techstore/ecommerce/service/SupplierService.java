package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.SupplierRequest;
import com.techstore.ecommerce.object.entity.jpa.Supplier;
import com.techstore.ecommerce.object.entity.jpa.Supplier;
import com.techstore.ecommerce.object.mapper.SupplierMapper;
import com.techstore.ecommerce.repository.jpa.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepo;
    private final SupplierMapper supplierMapper;

    public List<Supplier> findAllSuppliers(){
        return supplierRepo.findAll();
    }

    public Supplier findSupplierById(long id) {
        return supplierRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any supplier with id " + id));
    }

    public Supplier createSupplier(SupplierRequest request) {
        Supplier supplier = supplierMapper.createEntityFromRequest(request);
        existingSupplier(supplier);
        return supplierRepo.save(supplier);
    }

    public Supplier updateSupplier(long id, SupplierRequest request) {
        Supplier supplier = findSupplierById(id);

        supplierMapper.update(supplier, request);

        return supplierRepo.save(supplier);
    }

    public void deleteSupplier(long id) {
        Supplier supplier = findSupplierById(id);
        supplierRepo.delete(supplier);
    }

    public void existingSupplier(Supplier supplier){
        boolean existing = supplierRepo.existsByName(supplier.getName());
        if(existing){
            throw new EntityExistsException("Supplier name "+supplier.getName()+" already exists");
        }
    }
}
