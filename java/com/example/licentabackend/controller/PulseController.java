package com.example.licentabackend.controller;


import com.example.licentabackend.model.Pulse;
import com.example.licentabackend.service.PulseService;
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
@RequestMapping(value = "/server/pulseList")
@Tag(name = "Companies", description = "API for patiens management.")
public class PulseController {

    private final PulseService pulseService;

    public PulseController(PulseService pulseService) {
        this.pulseService = pulseService;
    }

    @Operation(
            summary = "Get all the pulse permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pulse returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pulse.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pulse not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Pulse> getAllPulse() {
        return pulseService.getAll();
    }

    @Operation(
            summary = "Get a pulse by its id",
            description = "The operation searches through the repository of pulse and, if found, returns the pulse by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pulse found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pulse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pulse not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pulse> findById(
            @Parameter(description = "id of pulse to be searched")
            @PathVariable Long id) {
        Optional<Pulse> pulse = pulseService.findById(id);
        return pulse.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a pulse by its id",
            description = "The operation searches through the repository of pulse and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pulse deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pulse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pulse not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of pulse to be deleted")
            @PathVariable Long id) {
        pulseService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new pulse",
            description = "The operation creates a pulse and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pulse created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pulse.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Pulse> newBloodPressure(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New pulse data")
            @RequestBody Pulse pulse) {
        Pulse savedPulse = pulseService.addPulse(pulse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPulse);
    }

    @Operation(
            summary = "Update of the pulse",
            description = "The operation updates all the information of the pulse with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pulse updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pulse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pulse not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pulse> updatedPulse(
            @Parameter(description = "id of pulse to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Pulse data to be updated")
            @RequestBody Pulse pulse) {
        Pulse updatedPulse = pulseService.updatePulse(id, pulse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPulse);
    }

}
