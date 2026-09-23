package com.lean.news;

import com.lean.news.enums.CategoryEnum;
import com.lean.news.model.entity.Category;
import com.lean.news.repository.ICategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CategorySeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CategorySeeder.class);

    @Autowired
    private ICategoryRepository ICategoryRepository;

    @Override
    public void run(String... args) {

        if (ICategoryRepository.count() == 0) {

            List<Category> categories = Arrays.stream(CategoryEnum.values())
                    .map(category -> {
                        Category c = new Category();
                        c.setName(category.name());
                        return c;
                    })
                    .toList();

            ICategoryRepository.saveAll(categories);

            logger.info("Categorías cargadas");
        }
    }
}