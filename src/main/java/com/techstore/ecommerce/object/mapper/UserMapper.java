package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.User;
import com.techstore.ecommerce.object.model.Address;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import com.techstore.ecommerce.object.model.FullName;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.dto.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roleName", expression = "java(user.getRole().getName())")
    UserResponse toResponseModel(User user);

    @Mapping(target = "roleName", expression = "java(user.getRole().getName())")
    @Mapping(target = "permissions",
            expression = "java(new java.util.ArrayList(user.getRole().getAuthorities()))")
    AuthenticationInfo toAuthInfo(User user);

    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "image", ignore = true)
    User createEntityFromRequest(UserRequest request);

    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "image", ignore = true)
    void update(@MappingTarget User user, UserRequest request);

    @AfterMapping
    default void getPropertiesAsString(@MappingTarget User user, UserRequest request) {
        FullName fullName = request.getFullName();
        String fullNameString = String.format("%s %s", fullName.getFirstName(), fullName.getLastName());
        user.setFullName(fullNameString);

        Address address = request.getAddress();
        String addressString = String.format("%s, %s, %s, %s",
                                             address.getStreet(), address.getWard(),
                                             address.getDistrict(), address.getCity());
        user.setAddress(addressString);
    }
}
