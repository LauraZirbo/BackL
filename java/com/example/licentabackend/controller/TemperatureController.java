package com.example.licentabackend.controller;

import com.example.licentabackend.model.Temperature;
import com.example.licentabackend.service.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/server/temperatureList")
@Tag(name = "Companies", description = "API for patiens management.")
public class TemperatureController {
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Operation(
            summary = "Get all the temperatures permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperatures returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Temperature.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Temperature not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Temperature> getAllTemperatures() {
        return temperatureService.getAll();
    }

    @Operation(
            summary = "Get a temperature by its id",
            description = "The operation searches through the repository of temperatures and, if found, returns the temperature by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperature found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Temperature.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Temperature not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Temperature> findById(
            @Parameter(description = "id of temperature to be searched")
            @PathVariable Long id) {
        Optional<Temperature> temperature = temperatureService.findById(id);
        return temperature.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a temperature by its id",
            description = "The operation searches through the repository of temperatures and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperature deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Temperature.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Temperature not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of temperature to be deleted")
            @PathVariable Long id) {
        temperatureService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new temperature",
            description = "The operation creates a temperature and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Temperature created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Temperature.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Temperature> newTemperature(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New temperature data")
            @RequestBody Temperature temperature) {
        Temperature savedTemperature = temperatureService.addTemperature(temperature);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTemperature);
    }

    @Operation(
            summary = "Update of the temperature",
            description = "The operation updates all the information of the temperature with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperature updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Temperature.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Temperature not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Temperature> updateTemperature(
            @Parameter(description = "id of temperature to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Temperature data to be updated")
            @RequestBody Temperature temperature) {
        Temperature updatedTemperature = temperatureService.updateTemperature(id, temperature);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedTemperature);
    }
}
