package com.lean.news.service;

import com.lean.news.enums.Rol;
import com.lean.news.exception.UserNotFound;
import com.lean.news.model.entity.User;
import com.lean.news.model.mapper.UserMapper;
import com.lean.news.model.repository.UserRepository;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.request.UpdateUserRequest;
import com.lean.news.rest.response.ListUsersResponse;
import com.lean.news.rest.response.UserResponse;
import com.lean.news.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse create(CreateUserRequest createUserRequest) {
        User user = userMapper.toUser(createUserRequest);
        user.setActive(true);
        user.setRol(Rol.READER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void delete(String id) {
        User user = findById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public ListUsersResponse listUsers() {
        List<User> listUsers = userRepository.findAll();
        if (listUsers.isEmpty()){
            throw new UserNotFound("No hay usuarios");
        }
        ListUsersResponse listUsersResponse = new ListUsersResponse();
        listUsersResponse.setUsers(userMapper.toListUserResponse(listUsers));
        return listUsersResponse;
    }

    @Override
    public UserResponse update(String id, UpdateUserRequest updateUserRequest) {
        User user = findById(id);
        User userUpdate = updateValues(updateUserRequest, user);
        userRepository.save(userUpdate);
        return userMapper.toUserResponse(userUpdate);
    }

//    @Override
//    public void registerOrUpdateUser(String firstname, String lastname, String email) {
//
//    }

    private User findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFound("El usuario no existe");
        } else {
            return optionalUser.get();
        }
    }

    private User updateValues(UpdateUserRequest updateUserRequest, User user) {

        String name = updateUserRequest.getName();
        if (name != null) {
            user.setName(name);
        }

        String lastName = updateUserRequest.getLastName();
        if (lastName != null) {
            user.setLastName(lastName);
        }

        String email = updateUserRequest.getEmail();
        if (email != null) {
            user.setEmail(email);
        }

        String password = updateUserRequest.getPassword();
        if (password != null) {
            user.setPassword(password);
        }

        return user;
    }

    public List<String> getRoleForUser(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            List<String> roles = new ArrayList<>();
            roles.add(user.getRol().name());

            return roles;

        }
        return Collections.singletonList("USER");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));


        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(user.getRol().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                "N/A",  // Contraseña ficticia o dummy
                authorities  // role convertido a GrantedAuthority
        );
    }

    public User getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLogged = authentication.getName();
        System.out.println("AUTHENTICATION " + authentication);
        System.out.println("emaillogged " + emailLogged);

        Optional<User> optionalUser = findByEmail(emailLogged);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFound("Usuario no encontrado");
        }
    }
}
