package com.example.licentabackend.controller;

import com.example.licentabackend.model.HeartRate;
import com.example.licentabackend.service.HeartRateService;
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
@RequestMapping(value = "/server/heartRateList")
@Tag(name = "Companies", description = "API for patiens management.")
public class HeartRateController {
    private final HeartRateService heartRateService;

    public HeartRateController(HeartRateService heartRateService) {
        this.heartRateService = heartRateService;
    }

    @Operation(
            summary = "Get all the heartRate permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HeartRate returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HeartRate.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "HeartRate not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<HeartRate> getAllHeartRate() {
        return heartRateService.getAll();
    }

    @Operation(
            summary = "Get a heartRate by its id",
            description = "The operation searches through the repository of heartRate and, if found, returns the heartRate by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HeartRate found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HeartRate.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "HeartRate not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HeartRate> findById(
            @Parameter(description = "id of heartRate to be searched")
            @PathVariable Long id) {
        Optional<HeartRate> heartRate = heartRateService.findById(id);
        return heartRate.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a heartRate by its id",
            description = "The operation searches through the repository of heartRate and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HeartRate deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HeartRate.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "HeartRate not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of heartRate to be deleted")
            @PathVariable Long id) {
        heartRateService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new heartRate",
            description = "The operation creates a heartRate and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "HeartRate created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HeartRate.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<HeartRate> newBloodPressure(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New heartRate data")
            @RequestBody HeartRate heartRate) {
        HeartRate savedHeartRate = heartRateService.addHeartRate(heartRate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedHeartRate);
    }

    @Operation(
            summary = "Update of the heartRate",
            description = "The operation updates all the information of the heartRate with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HeartRate updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HeartRate.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "HeartRate not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HeartRate> updatedHeartRate(
            @Parameter(description = "id of heartRate to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "HeartRate data to be updated")
            @RequestBody HeartRate heartRate) {
        HeartRate updatedHeartRate = heartRateService.updateHeartRate(id, heartRate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHeartRate);
    }
}
