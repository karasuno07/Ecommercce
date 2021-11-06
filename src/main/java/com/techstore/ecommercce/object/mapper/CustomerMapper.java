package com.techstore.ecommercce.object.mapper;

import com.techstore.ecommercce.object.entity.Customer;
import com.techstore.ecommercce.object.model.Address;
import com.techstore.ecommercce.object.model.FullName;
import com.techstore.ecommercce.object.request.CustomerRequest;
import com.techstore.ecommercce.object.response.CustomerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerResponse toResponseModel(Customer customer);

    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "image", ignore = true)
    Customer createEntityFromRequest(CustomerRequest request);

    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "image", ignore = true)
    void update(@MappingTarget Customer customer, CustomerRequest request);

    @AfterMapping
    default void getPropertiesAsString(@MappingTarget Customer customer, CustomerRequest request) {
        FullName fullName = request.getFullName();
        String fullNameString = String.format("%s %s", fullName.getFirstName(), fullName.getLastName());
        customer.setFullName(fullNameString);

        Address address = request.getAddress();
        String addressString = String.format("%s, %s, %s, %s",
                                             address.getStreet(), address.getWard(),
                                             address.getDistrict(), address.getCity());
        customer.setAddress(addressString);
    }
}
