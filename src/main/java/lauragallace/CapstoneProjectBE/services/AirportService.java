package lauragallace.CapstoneProjectBE.services;

import lauragallace.CapstoneProjectBE.entities.Airport;
import lauragallace.CapstoneProjectBE.exceptions.NotFoundException;
import lauragallace.CapstoneProjectBE.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirportService {
@Autowired
private AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(UUID id) {
        return airportRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public List<Airport> getAirportsByName(String name) {
        return airportRepository.findByName(name);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirportName(UUID id, String newName) {
        Airport found = this.getAirportById(id);
        found.setName(newName);
        return airportRepository.save(found);
    }

    public boolean deleteAirport(UUID id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Airport> getAirportsByDepartureFlight(UUID flightId) {
        return airportRepository.findByFlightListDepartureId(flightId);
    }

    public List<Airport> getAirportsByArrivalFlight(UUID flightId) {
        return airportRepository.findByFlightListArrivalId(flightId);
    }
}
