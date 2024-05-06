package com.lean.news;

import com.lean.news.enums.CategoryEnum;
import com.lean.news.model.entity.Category;
import com.lean.news.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class CategorySeeder implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {

            List<Category> categories = new ArrayList<>();
            Category cat1 = new Category();
            cat1.setName(CategoryEnum.LIBROS.toString());

            Category cat2 = new Category();
            cat2.setName(CategoryEnum.JUEGOS.toString());

            Category cat3 = new Category();
            cat3.setName(CategoryEnum.SERIES.toString());

            Category cat4 = new Category();
            cat4.setName(CategoryEnum.PELICULAS.toString());

            categories.add(cat1);
            categories.add(cat2);
            categories.add(cat3);
            categories.add(cat4);

            System.out.println(categories);

            categoryRepository.saveAll(categories);


        }
    }
}
