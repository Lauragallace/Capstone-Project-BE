package lauragallace.CapstoneProjectBE.services;

import lauragallace.CapstoneProjectBE.entities.Flight;
import lauragallace.CapstoneProjectBE.entities.Reservation;
import lauragallace.CapstoneProjectBE.entities.User;
import lauragallace.CapstoneProjectBE.exceptions.BadRequestException;
import lauragallace.CapstoneProjectBE.payloads.airports.ReservationDTO;
import lauragallace.CapstoneProjectBE.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private FlightService flightService;

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(UUID id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(ReservationDTO reservationDTO, User customer) {
        Flight flight = flightService.getFlightById(reservationDTO.flightId()).get();
        if(flight.getRemainingPlaces() == 0){
            throw new BadRequestException("flight full");
        }
        flight.setRemainingPlaces(flight.getRemainingPlaces()-1);
        flightService.updateFlight(flight.getId(),flight);
        Reservation reservation1 = new Reservation();
        reservation1.setFlight(flight);
        reservation1.setCustomer(customer);

        return reservationRepository.save(reservation1);
    }

    public Reservation updateReservation(UUID id, Reservation updatedReservation) {
        Optional<Reservation> existingReservationOptional = reservationRepository.findById(id);
        if (existingReservationOptional.isPresent()) {
            Reservation existingReservation = existingReservationOptional.get();
            existingReservation.setFlight(updatedReservation.getFlight());
            return reservationRepository.save(existingReservation);
        } else {
            return null;
        }
    }

    public boolean deleteReservation(UUID id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
