package com.example.backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Error response structure")
public class ErrorResponse {

    @Schema(description = "Short description of the error", example = "Validation failed")
    private String error;

    @Schema(description = "Details about what went wrong")
    private List<String> details;
}

