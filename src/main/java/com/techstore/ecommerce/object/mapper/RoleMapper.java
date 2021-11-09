package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.constant.Authorities;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.dto.response.RoleResponse;
import com.techstore.ecommerce.object.entity.jpa.Role;
import org.mapstruct.*;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

    RoleResponse toResponseModel(Role role);

    @Mapping(target = "authorities", ignore = true)
    Role createEntityFromRequest(RoleRequest request);

    @Mapping(target = "authorities", ignore = true)
    void update(@MappingTarget Role role, RoleRequest request);
}
