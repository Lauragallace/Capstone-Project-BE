package lauragallace.CapstoneProjectBE.payloads.airports;

import lauragallace.CapstoneProjectBE.entities.enums.FlightClass;
import org.hibernate.validator.cfg.defs.UUIDDef;

import java.time.LocalDate;
import java.util.UUID;

public record SearchFlightDTO(
        LocalDate flightDate,
        UUID departureAirport,
        UUID arrivalAirport,
        FlightClass flightClass
) {
}
