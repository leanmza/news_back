package com.lean.news.model.mapper;

import com.lean.news.model.entity.User;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-19T13:39:56-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.20 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest createUserRequest) {
        if ( createUserRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( createUserRequest.getName() );
        user.lastName( createUserRequest.getLastName() );
        user.email( createUserRequest.getEmail() );
        user.password( createUserRequest.getPassword() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        if ( user.getId() != null ) {
            userResponse.setId( String.valueOf( user.getId() ) );
        }
        userResponse.setName( user.getName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setRol( user.getRol() );
        userResponse.setActive( user.isActive() );

        return userResponse;
    }

    @Override
    public List<UserResponse> toListUserResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }
}
