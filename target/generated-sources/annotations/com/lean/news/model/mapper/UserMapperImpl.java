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
    date = "2024-05-07T13:32:16-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest createUserRequest) {
        if ( createUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( createUserRequest.getName() );
        user.setLastName( createUserRequest.getLastName() );
        user.setEmail( createUserRequest.getEmail() );
        user.setPassword( createUserRequest.getPassword() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
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
