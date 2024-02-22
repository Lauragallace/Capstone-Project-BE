package lauragallace.CapstoneProjectBE.controllers;

import com.cloudinary.Search;
import lauragallace.CapstoneProjectBE.entities.Flight;
import lauragallace.CapstoneProjectBE.payloads.airports.FlightDTO;
import lauragallace.CapstoneProjectBE.payloads.airports.SearchFlightDTO;
import lauragallace.CapstoneProjectBE.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping("/getFiltered")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsFiltered(@RequestBody SearchFlightDTO searchFlightDTO) {
        return flightService.getAllFlightsFiltered(searchFlightDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable UUID id) {
        Flight flight = flightService.getFlightById(id).orElse(null);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDTO flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable UUID id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID id) {
        boolean deleted = flightService.deleteFlight(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
