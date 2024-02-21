package lauragallace.CapstoneProjectBE.controllers;


import lauragallace.CapstoneProjectBE.entities.Airport;
import lauragallace.CapstoneProjectBE.payloads.airports.AirportDTO;
import lauragallace.CapstoneProjectBE.payloads.airports.UpdateAirportNameDTO;
import lauragallace.CapstoneProjectBE.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable UUID id) {
        Airport airport = airportService.getAirportById(id);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody AirportDTO airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Airport> updateAirport(@PathVariable UUID id, @RequestBody UpdateAirportNameDTO body) {
        Airport updatedAirport = airportService.updateAirportName(id,body.newName());
        if (updatedAirport != null) {
            return new ResponseEntity<>(updatedAirport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable UUID id) {
        boolean deleted = airportService.deleteAirport(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}