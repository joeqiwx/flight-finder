package com.example.backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Input payload containing lowercase characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightCountRequest {

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^[a-z]*$", message = "Only lowercase a-z characters allowed")
    @Schema(description = "Lowercase characters to evaluate", example = "flightflight")
    private String input;
}
