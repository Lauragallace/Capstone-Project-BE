package lauragallace.CapstoneProjectBE.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private User customer;
    @ManyToOne
    @JoinColumn (name= "flight_id")
    private Flight flight;
}
