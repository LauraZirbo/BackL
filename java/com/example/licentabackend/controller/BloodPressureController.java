package com.example.licentabackend.controller;


import com.example.licentabackend.model.BloodPressure;
import com.example.licentabackend.service.BloodPressureService;
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
@RequestMapping(value = "/server/bloodPressureList")
@Tag(name = "Companies", description = "API for patiens management.")
public class BloodPressureController {
    private final BloodPressureService bloodPressureService;

    public BloodPressureController(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @Operation(
            summary = "Get all the bloodPressure permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BloodPressure returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BloodPressure.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "BloodPressure not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<BloodPressure> getAllBloodPressure() {
        return bloodPressureService.getAll();
    }

    @Operation(
            summary = "Get a bloodPressure by its id",
            description = "The operation searches through the repository of bloodPressure and, if found, returns the bloodPressure by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BloodPressure found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BloodPressure.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "BloodPressure not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BloodPressure> findById(
            @Parameter(description = "id of bloodPressure to be searched")
            @PathVariable Long id) {
        Optional<BloodPressure> bloodPressure = bloodPressureService.findById(id);
        return bloodPressure.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a bloodPressure by its id",
            description = "The operation searches through the repository of bloodPressure and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BloodPressure deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BloodPressure.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "BloodPressure not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of bloodPressure to be deleted")
            @PathVariable Long id) {
        bloodPressureService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new bloodPressure",
            description = "The operation creates a bloodPressure and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "BloodPressure created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BloodPressure.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<BloodPressure> newBloodPressure(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New bloodPressure data")
            @RequestBody BloodPressure bloodPressure) {
        BloodPressure savedBloodPressure = bloodPressureService.addBloodPressure(bloodPressure);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBloodPressure);
    }

    @Operation(
            summary = "Update of the bloodPressure",
            description = "The operation updates all the information of the bloodPressure with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BloodPressure updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BloodPressure.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "BloodPressure not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BloodPressure> updatedBloodPressure(
            @Parameter(description = "id of bloodPressure to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "BloodPressure data to be updated")
            @RequestBody BloodPressure bloodPressure) {
        BloodPressure updatedBloodPressure = bloodPressureService.updateBloodPressure(id, bloodPressure);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedBloodPressure);
    }
}
