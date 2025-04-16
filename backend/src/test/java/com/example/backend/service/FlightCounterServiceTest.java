package com.example.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightCounterServiceTest {

    private FlightCounterService flightCounterService;

    @BeforeEach
    void setUp() {
        flightCounterService = new FlightCounterService();
    }

    @Test
    void testCountFlights_exactSingleFlight() {
        String input = "flight";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(1, result, "Should find exactly one 'flight' in the string 'flight'");
    }

    @Test
    void testCountFlights_multipleFlights() {
        String input = "flightflight";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(2, result, "Should find two 'flight' occurrences in the string 'flightflight'");
    }

    @Test
    void testCountFlights_noFlights() {
        String input = "abcde";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(0, result, "Should find zero occurrences when letters are missing");
    }

    @Test
    void testCountFlights_partialMatch() {
        String input = "flightfliht";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(1, result, "Should only form one complete 'flight' when letters are missing");
    }

    @Test
    void testCountFlights_largeString() {
        String input = "fllllliiiiigggggggghhhhhhhhtttttttflightflightflight";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(4, result, "Should form three 'flight' occurrences from the extra letters");
    }

    @Test
    void testCountFlights_emptyString() {
        String input = "";
        int result = flightCounterService.countFlights(input);
        Assertions.assertEquals(0, result, "Should find zero occurrences in an empty string");
    }
}