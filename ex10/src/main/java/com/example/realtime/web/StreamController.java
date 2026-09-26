package com.example.realtime.web;

import com.example.realtime.model.SensorReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StreamController {

    private final ReactiveMongoTemplate template;

    @Autowired
    public StreamController(ReactiveMongoTemplate template) {
        this.template = template;
    }

    // SSE stream of new SensorReading changes
    @GetMapping(path = "/stream/readings", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Map<String, Object>> streamReadings() {
        ChangeStreamOptions options = ChangeStreamOptions.builder()
                .filter(Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("operationType").in("insert", "update", "replace"))
                ))
                .returnFullDocumentOnUpdate()
                .build();

        return template.changeStream("sensor_readings", options, SensorReading.class)
                .map(event -> event.getBody())
                .filter(reading -> reading != null)
                .map(reading -> Map.of(
                        "timestamp", reading.getTimestamp(),
                        "value", reading.getValue()
                ));
    }

    // Quick insert API to simulate data (POST /api/readings?value=42.5)
    @PostMapping("/api/readings")
    @ResponseBody
    public Map<String, Object> addReading(@RequestParam double value) {
        SensorReading r = new SensorReading(Instant.now(), value);
        return template.save(r).map(saved -> {
            Map<String, Object> result = new HashMap<>();
            result.put("id", saved.getId());
            result.put("timestamp", saved.getTimestamp());
            result.put("value", saved.getValue());
            return result;
        }).block();
    }
}