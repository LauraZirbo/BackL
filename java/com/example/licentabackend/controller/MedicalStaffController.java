package com.example.licentabackend.controller;

import com.example.licentabackend.model.MedicalStaff;
import com.example.licentabackend.service.MedicalStaffService;
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
@RequestMapping(value = "/server/medicalStaff")
@Tag(name = "MedicalStaff", description = "API for medicalStaff management.")
public class MedicalStaffController {


    private final MedicalStaffService medicalStaffService;


    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @Operation(
            summary = "Get all the medicalStaff permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "MedicalStaff not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffService.getAll();
    }

    @Operation(
            summary = "Get a medicalStaff by its id",
            description = "The operation searches through the repository of medicalStaff and, if found, returns the medicalStaff by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "MedicalStaff not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MedicalStaff> findById(
            @Parameter(description = "id of medicalStaff to be searched")
            @PathVariable Long id) {
        Optional<MedicalStaff> medicalStaff = medicalStaffService.findById(id);
        return medicalStaff.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Get a medicalStaff by its email",
            description = "The operation searches through the repository of medicalStaff and, if found, returns the medicalStaff by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "MedicalStaff not found.", content = @Content)})
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<MedicalStaff> findByEmail(
            @Parameter(description = "id of medicalStaff to be searched")
            @PathVariable String email) {
        Optional<MedicalStaff> medicalStaff = medicalStaffService.findByEmail(email);
        return medicalStaff.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a medicalStaff by its id",
            description = "The operation searches through the repository of medicalStaff and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "MedicalStaff not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of medicalStaff to be deleted")
            @PathVariable Long id) {
        medicalStaffService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new medicalStaff",
            description = "The operation creates a medicalStaff and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "MedicalStaff created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<MedicalStaff> newMedicalStaff(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New medicalStaff data")
            @RequestBody MedicalStaff medicalStaff) {
        MedicalStaff savedMedicalStaff = medicalStaffService.addMedicalStaff(medicalStaff);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedMedicalStaff);
    }

    @Operation(
            summary = "Update of the medicalStaff",
            description = "The operation updates all the information of the medicalStaff with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MedicalStaff.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "MedicalStaff not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MedicalStaff> updateMedicalStaff(
            @Parameter(description = "id of medicalStaff to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "MedicalStaff data to be updated")
            @RequestBody MedicalStaff medicalStaff) {
        MedicalStaff updatedMedicalStaff = medicalStaffService.updateMedicalStaff(id, medicalStaff );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedMedicalStaff);
    }

}
