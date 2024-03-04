package lauragallace.CapstoneProjectBE.repositories;

import lauragallace.CapstoneProjectBE.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :customerId AND r.flight.id = :flightId")
    public List<Reservation> getReservationByUserAndFlight(UUID customerId,UUID flightId);
}
