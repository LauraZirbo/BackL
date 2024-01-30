package com.example.licentabackend.controller;

import com.example.licentabackend.model.Patient;
import com.example.licentabackend.service.PatientService;
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
@RequestMapping(value = "/server/patients")
@Tag(name = "Companies", description = "API for patiens management.")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(
            summary = "Get all the patient permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }

    @Operation(
            summary = "Get a patient by its id",
            description = "The operation searches through the repository of patient and, if found, returns the patient by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Patient> findById(
            @Parameter(description = "id of patient to be searched")
            @PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


    @Operation(
            summary = "Get a patient by its cnp",
            description = "The operation searches through the repository of patient and, if found, returns the patient by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found.", content = @Content)})
    @RequestMapping(value = "/Cnp/{cnp}", method = RequestMethod.GET)
    public ResponseEntity<Patient> findByCnp(
            @Parameter(description = "cnp of patient to be searched")
            @PathVariable String cnp) {
        Optional<Patient> patient = patientService.findByCnp(cnp);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a patient by its id",
            description = "The operation searches through the repository of patient and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of patient to be deleted")
            @PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new patient",
            description = "The operation creates a patient and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Patient> newPatient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New patient data")
            @RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPatient);
    }

    @Operation(
            summary = "Update of the patient",
            description = "The operation updates all the information of the patient with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatedPatient(
            @Parameter(description = "id of patient to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Patient data to be updated")
            @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPatient);
    }

}
