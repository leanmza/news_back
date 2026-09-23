package com.lean.news.model.mapper;

import com.lean.news.dto.request.UserRequestDTO;
import com.lean.news.dto.response.UserResponseDTO;
import com.lean.news.model.entity.UserSec;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-23T13:04:28-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.20 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserSec toUser(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        UserSec.UserSecBuilder userSec = UserSec.builder();

        userSec.name( userRequestDTO.getName() );
        userSec.lastName( userRequestDTO.getLastName() );
        userSec.email( userRequestDTO.getEmail() );
        userSec.password( userRequestDTO.getPassword() );

        return userSec.build();
    }

    @Override
    public UserResponseDTO toUserResponse(UserSec userSec) {
        if ( userSec == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        if ( userSec.getId() != null ) {
            userResponseDTO.setId( String.valueOf( userSec.getId() ) );
        }
        userResponseDTO.setName( userSec.getName() );
        userResponseDTO.setLastName( userSec.getLastName() );
        userResponseDTO.setEmail( userSec.getEmail() );
        userResponseDTO.setPassword( userSec.getPassword() );

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> toListUserResponse(List<UserSec> userSecs) {
        if ( userSecs == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( userSecs.size() );
        for ( UserSec userSec : userSecs ) {
            list.add( toUserResponse( userSec ) );
        }

        return list;
    }
}
