package com.example.realtime.config;

import com.example.realtime.model.SensorReading;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@Configuration
public class DemoDataRunner {

    @Bean
    CommandLineRunner seed(ReactiveMongoTemplate template) {
        return args -> {
            Random rnd = new Random();
            Flux.interval(Duration.ofSeconds(2))
                .flatMap(t -> template.save(new SensorReading(Instant.now(), 20 + rnd.nextDouble() * 10)))
                .subscribe();
        };
    }
}