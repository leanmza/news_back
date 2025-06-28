package com.lean.news;

import com.lean.news.enums.Rol;
import com.lean.news.model.entity.User;
import com.lean.news.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            String encryptPass = new BCryptPasswordEncoder().encode("123");
            User admin = new User();
            admin.setName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encryptPass);
            admin.setRol(Rol.ADMIN);
            admin.setActive(true);

            System.out.println(admin);

            userRepository.save(admin);

        }
    }
}
