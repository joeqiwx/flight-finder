package com.example.backend.controller;

import com.example.backend.dtos.ErrorResponse;
import com.example.backend.dtos.FlightCountRequest;
import com.example.backend.dtos.FlightCountResponse;
import com.example.backend.service.FlightCounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Flight Counter", description = "APIs for counting 'flight' instances")
public class FlightFinderController {

    private final FlightCounterService flightCounterService;

    public FlightFinderController(FlightCounterService flightCounterService) {
        this.flightCounterService = flightCounterService;
    }

    @Operation(
            summary = "Count 'flight' occurrences from input characters",
            description = "Receives a JSON body with input string, returns how many times 'flight' can be formed"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = FlightCountResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/count")
    public FlightCountResponse getFlightCount(@Valid @RequestBody FlightCountRequest request) {
        int count = flightCounterService.countFlights(request.getInput());
        return new FlightCountResponse(count);
    }
}
