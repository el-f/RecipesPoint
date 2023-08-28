package com.recipespoint.backend.config;

import com.recipespoint.backend.dal.model.UserEntity;
import com.recipespoint.backend.dal.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner initData(IUserRepository userRepository) {
        return args -> {
            if (userRepository.findById("dummy-user").isEmpty()) {
                UserEntity dummyUser = new UserEntity();
                dummyUser.setId("dummy-user");
                dummyUser.setUsername("dummy-user");
                userRepository.save(dummyUser);
            }
        };
    }

}
