package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.dto.response.UserResponse;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-28T17:22:21+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.13 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponseModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setGender( user.isGender() );
        userResponse.setDateOfBirth( user.getDateOfBirth() );
        userResponse.setPhoneNumber( user.getPhoneNumber() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setAddress( user.getAddress() );
        userResponse.setActive( user.isActive() );

        userResponse.setRoleName( user.getRole().getName() );

        return userResponse;
    }

    @Override
    public AuthenticationInfo toAuthInfo(User user) {
        if ( user == null ) {
            return null;
        }

        AuthenticationInfo authenticationInfo = new AuthenticationInfo();

        authenticationInfo.setId( user.getId() );
        authenticationInfo.setUsername( user.getUsername() );
        authenticationInfo.setFullName( user.getFullName() );
        authenticationInfo.setImage( user.getImage() );

        authenticationInfo.setRoleName( user.getRole().getName() );
        authenticationInfo.setPermissions( new java.util.ArrayList(user.getRole().getAuthorities()) );

        return authenticationInfo;
    }

    @Override
    public User createEntityFromRequest(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setGender( request.isGender() );
        user.setDateOfBirth( request.getDateOfBirth() );
        user.setPhoneNumber( request.getPhoneNumber() );
        user.setEmail( request.getEmail() );

        getPropertiesAsString( user, request );

        return user;
    }

    @Override
    public void update(User user, UserRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getUsername() != null ) {
            user.setUsername( request.getUsername() );
        }
        if ( request.getPassword() != null ) {
            user.setPassword( request.getPassword() );
        }
        user.setGender( request.isGender() );
        if ( request.getDateOfBirth() != null ) {
            user.setDateOfBirth( request.getDateOfBirth() );
        }
        if ( request.getPhoneNumber() != null ) {
            user.setPhoneNumber( request.getPhoneNumber() );
        }
        if ( request.getEmail() != null ) {
            user.setEmail( request.getEmail() );
        }

        getPropertiesAsString( user, request );
    }
}
