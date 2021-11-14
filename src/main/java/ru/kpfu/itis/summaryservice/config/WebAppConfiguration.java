package ru.kpfu.itis.summaryservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * @author Roman Leontev
 * 22:42 10.11.2021
 * student ITIS
 */

@Configuration
@ComponentScan(basePackages = "ru.kpfu.itis.summaryservice")
@EntityScan(basePackages = "ru.kpfu.itis.summaryservice.entity")
@EnableMongoRepositories(basePackages = "ru.kpfu.itis.summaryservice.repository")
public class WebAppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
