package lauragallace.CapstoneProjectBE.payloads.airports;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightDTO(

        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        Integer places,
        String flightClass,
        Double price,
        String departureAirportCode,
        String arrivalAirportCode
) {}
