package com.example.backend.controller;

import com.example.backend.dtos.FlightCountRequest;
import com.example.backend.service.FlightCounterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightFinderController.class)
class FlightFinderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightCounterService flightCounterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnCountWhenInputIsValid() throws Exception {
        Mockito.when(flightCounterService.countFlights(any()))
                .thenReturn(2);

        FlightCountRequest request = new FlightCountRequest("flightflight");

        mockMvc.perform(post("/api/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2));
    }

    @Test
    void shouldReturnBadRequestForTooLongInput() throws Exception {
        FlightCountRequest request = new FlightCountRequest("a".repeat(101));

        mockMvc.perform(post("/api/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation failed"))
                .andExpect(jsonPath("$.details[0]").value("input: size must be between 0 and 100"));
    }
}