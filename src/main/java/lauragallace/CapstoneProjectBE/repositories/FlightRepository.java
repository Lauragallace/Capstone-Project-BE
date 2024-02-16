package lauragallace.CapstoneProjectBE.repositories;

import lauragallace.CapstoneProjectBE.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
