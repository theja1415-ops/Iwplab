package com.example.realtime.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document("sensor_readings")
public class SensorReading {
    
    @Id
    private String id;
    private Instant timestamp;
    private double value;

    public SensorReading() {}

    public SensorReading(Instant timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}