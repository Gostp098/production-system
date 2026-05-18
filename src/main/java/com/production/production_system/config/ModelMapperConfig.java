package com.production.production_system.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // STRICT matching prevents accidental mappings between fields
        // that happen to share the same name but belong to unrelated types
        mapper.getConfiguration()
                .setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
        return mapper;
    }
}
