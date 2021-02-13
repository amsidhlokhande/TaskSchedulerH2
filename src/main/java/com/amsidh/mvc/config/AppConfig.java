package com.amsidh.mvc.config;

import com.amsidh.mvc.repositories.TodoRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {TodoRepository.class})
public class AppConfig {
}
