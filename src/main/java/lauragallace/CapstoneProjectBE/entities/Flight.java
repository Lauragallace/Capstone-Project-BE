package lauragallace.CapstoneProjectBE.entities;

import jakarta.persistence.*;
import lauragallace.CapstoneProjectBE.entities.enums.FlightClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDateTime departureDate;
    private LocalDateTime ArrivalDate;
    private Integer places;
    @Enumerated (EnumType.STRING)
    private FlightClass flightClass;
    private Double price;
    @ManyToOne
    @JoinColumn (name = "airport_departure")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn (name = "airport_arrival")
    private Airport arrivalAirport;

}
