package com.sprashanthv.nodeanalysis.config;

import com.sprashanthv.nodeanalysis.service.CsvService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public CsvService csvService() {
        return mock(CsvService.class);
    }
}
