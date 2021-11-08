package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.filter.CustomerFilter;
import com.techstore.ecommerce.object.dto.request.CustomerRequest;
import com.techstore.ecommerce.object.entity.jpa.Customer;
import com.techstore.ecommerce.object.mapper.CustomerMapper;
import com.techstore.ecommerce.repository.jpa.CustomerRepository;
import com.techstore.ecommerce.repository.jpa.spec.CustomerSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class CustomerService{

    private final CustomerRepository customerRepo;
    private final CustomerMapper customerMapper;

    public Page<Customer> findAllCustomers(CustomerFilter filter) {
        Specification<Customer> specification = CustomerSpec.getSpecification(filter);
        return customerRepo.findAll(specification, filter.getPagination().getPageAndSort());
    }

    public Customer findCustomerById(long id) {
        return customerRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any customer with id " + id));
    }

    public Customer createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.createEntityFromRequest(request);

        existingCustomer(customer);

        // TODO: save image from multipart file

        return customerRepo.save(customer);
    }

    public Customer updateCustomer(long id, CustomerRequest request) {
        Customer customer = findCustomerById(id);
        customerMapper.update(customer, request);

        existingCustomer(customer);
        // TODO: save image from multipart file

        return customerRepo.save(customer);
    }

    public void activateCustomer(long id) {
        Customer customer = findCustomerById(id);
        customer.setActive(true);
        customerRepo.save(customer);
    }

    public void deactivateCustomer(long id) {
        Customer customer = findCustomerById(id);
        customer.setActive(false);
        customerRepo.save(customer);
    }

    public void existingCustomer(Customer customer){
        boolean existing = customerRepo.existsByUsername(customer.getUsername());
        if (existing) {
            throw new EntityExistsException("Username " + customer.getUsername() + " already exists");
        }

        existing = customerRepo.existsByEmail(customer.getEmail());
        if(existing){
            throw new EntityExistsException("Email " + customer.getEmail() + " already exists");
        }

        existing = customerRepo.existsByPhoneNumber(customer.getPhoneNumber());
        if(existing){
            throw new EntityExistsException("Phone number " + customer.getPhoneNumber() + " already exists");
        }
    }


}
