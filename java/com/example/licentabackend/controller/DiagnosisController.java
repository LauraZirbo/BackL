package com.example.licentabackend.controller;

import com.example.licentabackend.model.Diagnosis;
import com.example.licentabackend.service.DiagnosisService;
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
@RequestMapping(value = "/server/diagnosisList")
@Tag(name = "Companies", description = "API for diagnosis management.")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Operation(
            summary = "Get all the diagnosis permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnosis returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Diagnosis.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Diagnosis not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Diagnosis> getAllDiagnosises() {
        return diagnosisService.getAll();
    }

    @Operation(
            summary = "Get a diagnosis by its id",
            description = "The operation searches through the repository of diagnosis and, if found, returns the diagnosis by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnosis found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Diagnosis.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Diagnosis not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Diagnosis> findById(
            @Parameter(description = "id of diagnosis to be searched")
            @PathVariable Long id) {
        Optional<Diagnosis> diagnosis = diagnosisService.findById(id);
        return diagnosis.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a diagnosis by its id",
            description = "The operation searches through the repository of diagnosis and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnosis deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Diagnosis.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Diagnosis not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of diagnosis to be deleted")
            @PathVariable Long id) {
        diagnosisService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new diagnosis",
            description = "The operation creates a diagnosis and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diagnosis created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Diagnosis.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Diagnosis> newDiagnosis(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New diagnosis data")
            @RequestBody Diagnosis diagnosis) {
        Diagnosis savedDiagnosis = diagnosisService.addDiagnosis(diagnosis);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDiagnosis);
    }

    @Operation(
            summary = "Update of the diagnosis",
            description = "The operation updates all the information of the diagnosis with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnosis updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Diagnosis.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Diagnosis not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Diagnosis> updatedDiagnosis(
            @Parameter(description = "id of diagnosis to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Diagnosis data to be updated")
            @RequestBody Diagnosis diagnosis) {
        Diagnosis updatedDiagnosis = diagnosisService.updateDiagnosis(id, diagnosis);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedDiagnosis);
    }
}
