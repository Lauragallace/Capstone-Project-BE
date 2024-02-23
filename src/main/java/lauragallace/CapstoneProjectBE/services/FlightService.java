package lauragallace.CapstoneProjectBE.services;

import lauragallace.CapstoneProjectBE.entities.Flight;
import lauragallace.CapstoneProjectBE.payloads.airports.FlightDTO;
import lauragallace.CapstoneProjectBE.payloads.airports.SearchFlightDTO;
import lauragallace.CapstoneProjectBE.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {

    @Autowired
    private AirportService airportService;

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(UUID id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(FlightDTO flightDTO) {

        Flight flight = new Flight();
        flight.setFlightClass(flightDTO.flightClass());
        flight.setPrice(flightDTO.price());
        flight.setPlaces(flightDTO.places());
        flight.setRemainingPlaces(flightDTO.places());
        flight.setDepartureDate(flightDTO.departureDate());
        flight.setArrivalDate(flightDTO.arrivalDate());
        flight.setDepartureAirport(airportService.getAirportById(flightDTO.departureAirportCode()));
        flight.setArrivalAirport(airportService.getAirportById(flightDTO.arrivalAirportCode()));
        return flightRepository.save(flight);
    }

    public Flight updateFlight(UUID id, Flight updatedFlight) {
        Optional<Flight> existingFlightOptional = flightRepository.findById(id);
        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureDate(updatedFlight.getDepartureDate());
            existingFlight.setArrivalDate(updatedFlight.getArrivalDate());
            existingFlight.setPrice(updatedFlight.getPrice());


            return flightRepository.save(existingFlight);
        } else {
            return null;
        }
    }

    public boolean deleteFlight(UUID id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Flight> getAllFlightsFiltered(SearchFlightDTO searchFlightDTO){
        return flightRepository.getAllFlightsFiltered(
                searchFlightDTO.departureAirport(),
                searchFlightDTO.arrivalAirport(),
                searchFlightDTO.flightClass(),
                searchFlightDTO.flightDate());
    }
}
