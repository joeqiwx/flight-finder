package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightCounterService {

    private static final String TARGET = "flight";

    public int countFlights(String input) {
        Map<Character, Long> inputFreq = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        Map<Character, Long> targetFreq = TARGET.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        return targetFreq.entrySet().stream()
                .mapToInt(entry -> (int) (
                        inputFreq.getOrDefault(entry.getKey(), 0L) / entry.getValue()
                ))
                .min()
                .orElse(0);
    }
}
