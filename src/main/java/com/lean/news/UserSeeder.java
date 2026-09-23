package com.lean.news;

import com.lean.news.enums.Rol;
import com.lean.news.model.entity.UserSec;
import com.lean.news.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserSeeder implements CommandLineRunner {
//crea por única vez un usuario ADMIN si en la tabla de usuarios de la base de datos no hay ninguna entrada
    @Autowired
IUserRepository IUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserSeeder.class);

    @Override
    public void run(String... args) throws Exception {


        if (IUserRepository.count() == 0) {

            String encryptPass = new BCryptPasswordEncoder().encode("123");
            UserSec admin = new UserSec();
            admin.setName("admin");
            admin.setLastname("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encryptPass);
//            admin.setRol(Rol.ADMIN);
            admin.setEnabled(true);

            IUserRepository.save(admin);

            logger.info("user cargado");
        }
    }
}
