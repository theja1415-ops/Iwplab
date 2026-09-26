package com.example.realtime.repo;

import com.example.realtime.model.SensorReading;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SensorReadingRepository extends ReactiveMongoRepository<SensorReading, String> { 
}