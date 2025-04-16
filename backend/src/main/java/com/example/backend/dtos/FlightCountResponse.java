package com.example.backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Result indicating how many times 'flight' can be formed")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightCountResponse {

    @Schema(description = "Number of valid 'flight' words in input", example = "2")
    private int count;
}

