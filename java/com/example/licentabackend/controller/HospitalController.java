package com.example.licentabackend.controller;

import com.example.licentabackend.model.Hospital;
import com.example.licentabackend.service.HospitalService;
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
@RequestMapping(value = "/server/hospitals")
@Tag(name = "Companies", description = "API for hospitals management.")
public class HospitalController {



    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Operation(
            summary = "Get all the hospitals permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitals returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospital.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospital not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Hospital> getAllHospital() {
        return hospitalService.getAll();
    }

    @Operation(
            summary = "Get a hospital by its id",
            description = "The operation searches through the repository of hospital and, if found, returns the hospital by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospital.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospital not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hospital> findById(
            @Parameter(description = "id of hospital to be searched")
            @PathVariable Long id) {
        Optional<Hospital> hospital = hospitalService.findById(id);
        return hospital.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Deletion of a hospital by its id",
            description = "The operation searches through the repository of hospitals and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospital.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospital not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of hospital to be deleted")
            @PathVariable Long id) {
        hospitalService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new hospital",
            description = "The operation creates a hospital and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospital created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospital.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Hospital> newHospital(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New hospital data")
            @RequestBody Hospital hospital) {
        Hospital savedHospital = hospitalService.addHospital(hospital);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedHospital);
    }

    @Operation(
            summary = "Update of the hospital",
            description = "The operation updates all the information of the hospital with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospital.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospital not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hospital> updateHospital(
            @Parameter(description = "id of hospital to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hospital data to be updated")
            @RequestBody Hospital hospital) {
        Hospital updatedHospital = hospitalService.updateHospital(id, hospital);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHospital);
    }



}
