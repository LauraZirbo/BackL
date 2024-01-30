package com.example.licentabackend.controller;


import com.example.licentabackend.model.Medication;
import com.example.licentabackend.service.MedicationService;
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
@RequestMapping(value = "/server/medicationList")
@Tag(name = "Companies", description = "API for medication management.")
public class MedicationController {
    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @Operation(
            summary = "Get all the medication permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Medication> getAllMedication() {
        return medicationService.getAll();
    }

    @Operation(
            summary = "Get a medication by its id",
            description = "The operation searches through the repository of medication and, if found, returns the medication by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Medication> findById(
            @Parameter(description = "id of medication to be searched")
            @PathVariable Long id) {
        Optional<Medication> medication = medicationService.findById(id);
        return medication.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a medication by its id",
            description = "The operation searches through the repository of medication and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of medication to be deleted")
            @PathVariable Long id) {
        medicationService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new medication",
            description = "The operation creates a medication and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medication created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Medication> newMedication(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New medication data")
            @RequestBody Medication medication) {
        Medication savedMedication = medicationService.addMedication(medication);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedMedication);
    }

    @Operation(
            summary = "Update of the medication",
            description = "The operation updates all the information of the medication with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Medication> updatedMedication(
            @Parameter(description = "id of medication to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Medication data to be updated")
            @RequestBody Medication medication) {
        Medication updatedMedication = medicationService.updateMedication(id, medication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedMedication);
    }
}
