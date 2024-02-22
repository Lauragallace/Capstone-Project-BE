package lauragallace.CapstoneProjectBE.payloads.airports;

import lauragallace.CapstoneProjectBE.entities.enums.FlightClass;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightDTO(

        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        Integer places,
        FlightClass flightClass,
        Double price,
        UUID departureAirportCode,
        UUID arrivalAirportCode
) {}
