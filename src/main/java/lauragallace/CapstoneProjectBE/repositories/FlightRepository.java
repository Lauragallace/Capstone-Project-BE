package lauragallace.CapstoneProjectBE.repositories;

import lauragallace.CapstoneProjectBE.entities.Flight;
import lauragallace.CapstoneProjectBE.entities.enums.FlightClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    @Query("SELECT f FROM Flight f WHERE CAST(f.departureDate AS date)=:flightDate AND f.departureAirport.id=:departure AND f.arrivalAirport.id=:arrival AND f.flightClass=:flightClass")
    public List<Flight> getAllFlightsFiltered(UUID departure,
                                              UUID arrival,
                                              FlightClass flightClass,
                                              LocalDate flightDate);
}
