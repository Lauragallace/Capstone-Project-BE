package lauragallace.CapstoneProjectBE.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "airports")
@Getter
@Setter
public class Airport {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(unique = true)
    private String name;
    @OneToMany (mappedBy = "departureAirport")
    private List <Flight> flightListDeparture;
    @OneToMany (mappedBy = "arrivalAirport")
    private List <Flight> flightListArrival;


}
