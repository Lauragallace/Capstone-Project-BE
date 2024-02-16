package lauragallace.CapstoneProjectBE.repositories;

import lauragallace.CapstoneProjectBE.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {

    List<Airport> findByName(String name);

    List<Airport> findByFlightListDepartureId(UUID flightId);

    List<Airport> findByFlightListArrivalId(UUID flightId);


}
