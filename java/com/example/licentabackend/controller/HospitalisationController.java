package com.example.licentabackend.controller;

import com.example.licentabackend.model.Diagnosis;
import com.example.licentabackend.model.Hospitalisation;
import com.example.licentabackend.model.Medication;
import com.example.licentabackend.service.HospitalisationService;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/server/hospitalisations")
@Tag(name = "Companies", description = "API for hospitalisations management.")
public class HospitalisationController {

    private final HospitalisationService hospitalisationService;

    public HospitalisationController(HospitalisationService hospitalisationService) {
        this.hospitalisationService = hospitalisationService;
    }

    @Operation(
            summary = "Get all the hospitalisations permitted to the poweruser atlas.",
            description = "This operation is just demonstrating its security set to POWERUSER role only (not accessible by the user).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisations returned.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Hospitalisation> getAllHospitalisation() {
        return hospitalisationService.getAll();
    }

    @Operation(
            summary = "Get a hospitalisation by its id",
            description = "The operation searches through the repository of hospitalisation and, if found, returns the hospitalisation by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hospitalisation> findById(
            @Parameter(description = "id of hospitalisation to be searched")
            @PathVariable Long id) {
        Optional<Hospitalisation> hospitalisation = hospitalisationService.findById(id);
        return hospitalisation.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Get a the Last hospitalisation",
            description = "The operation searches through the repository of hospitalisation and, if found, returns the hospitalisation by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/getLast/{hospitalId}", method = RequestMethod.GET)
    public ResponseEntity<List<Hospitalisation>> newHospitalisation(@Parameter(description = "id of hospital to be searched")
                                                                    @PathVariable Long hospitalId) {
        List<Hospitalisation> hospitalisation = hospitalisationService.getLastHospitalisationOfAHospital(hospitalId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hospitalisation);
    }

    @Operation(
            summary = "Deletion of a hospitalisation by its id",
            description = "The operation searches through the repository of hospitalisations and, if found, deletes it.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation deleted.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteById(
            @Parameter(description = "id of hospitalisation to be deleted")
            @PathVariable Long id) {
        hospitalisationService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Operation(
            summary = "Creation of a new hospitalisation",
            description = "The operation creates a hospitalisation and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospitalisation created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content)})
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Hospitalisation> newHospitalisation(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New hospitalisation data")
            @RequestBody Hospitalisation hospitalisation) {
        Hospitalisation savedHospitalisation = hospitalisationService.addHospitalisation(hospitalisation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedHospitalisation);
    }

    @Operation(
            summary = "Update of the hospitalisation",
            description = "The operation updates all the information of the hospitalisation with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hospitalisation> updateHospitalisation(
            @Parameter(description = "id of hospitalisation to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hospital data to be updated")
            @RequestBody Hospitalisation hospitalisation) {
        Hospitalisation updatedHospitalisation = hospitalisationService.updateHospitalisation(id, hospitalisation);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHospitalisation);
    }

    @Operation(
            summary = "Update of the hospitalisation by adding diagnosis",
            description = "The operation updates all the information of the hospitalisation with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/addDiagnosis/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hospitalisation> addDiagnsosis(
            @Parameter(description = "id of hospitalisation to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hospital data to be updated")
            @RequestBody List<Diagnosis> diagnosisList) {
        Hospitalisation updatedHospitalisation = hospitalisationService.addHospitalisationDiagnosis(id, diagnosisList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHospitalisation);
    }

    @Operation(
            summary = "Update of the hospitalisation by adding to the medication",
            description = "The operation updates all the information of the hospitalisation with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/addMedication/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hospitalisation> addMedication(
            @Parameter(description = "id of hospitalisation to be updated")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hospital data to be updated")
            @RequestBody List<Medication> medicationList) {
        Hospitalisation updatedHospitalisation = hospitalisationService.addHospitalisationMedication(id, medicationList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHospitalisation);
    }

    @Operation(
            summary = "Discarge patient and end  of the hospitalisation",
            description = "The operation updates all the information of the hospitalisation with selected id and returns its final value.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalisation updated.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Hospitalisation.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Request is not authenticated.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Request is not authorized.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospitalisation not found.", content = @Content)})
    @RequestMapping(value = "/dischargePatient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hospitalisation> dischargePatient(
            @Parameter(description = "id of hospitalisation to be discherged")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hospital data to be updated")
            @RequestBody Date dischargeDate) {
        Hospitalisation updatedHospitalisation = hospitalisationService.dischargePatient(id, dischargeDate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedHospitalisation);
    }

}
