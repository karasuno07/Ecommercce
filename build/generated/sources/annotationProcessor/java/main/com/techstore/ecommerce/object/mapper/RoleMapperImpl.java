package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.dto.response.RoleResponse;
import com.techstore.ecommerce.object.entity.jpa.Role;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-07T15:58:33+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponse toResponseModel(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setId( role.getId() );
        roleResponse.setName( role.getName() );
        List<String> list = role.getAuthorities();
        if ( list != null ) {
            roleResponse.setAuthorities( new HashSet<String>( list ) );
        }

        return roleResponse;
    }

    @Override
    public Role createEntityFromRequest(RoleRequest request) {
        if ( request == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( request.getName() );

        return role;
    }

    @Override
    public void update(Role role, RoleRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            role.setName( request.getName() );
        }
    }
}
